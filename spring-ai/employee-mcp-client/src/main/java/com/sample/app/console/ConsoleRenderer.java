package com.sample.app.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

/**
 * Renders rich, coloured output to the terminal using JLine 3.
 *
 * <p>Supports a lightweight Markdown → ANSI subset: H1/H2/H3 headings, horizontal rules,
 * ordered/unordered lists, blockquotes, and inline <strong>bold</strong>, <em>italic</em>, and
 * {@code code} spans. Any unrecognised line is printed verbatim.
 */
public final class ConsoleRenderer {

  // ── JLine AttributedStyle palette ─────────────────────────────────────────
  //
  //  JLine colour indices:  0-7  → standard  \033[3Xm
  //                         8-15 → bright     \033[9(X-8)m
  //                         16+  → 256-colour \033[38;5;Xm
  //
  //  Convenience: BRIGHT (=8) | colour  gives the bright variant.

  private static final AttributedStyle RESET = AttributedStyle.DEFAULT;

  // Banner / headers
  private static final AttributedStyle BANNER =
      bold(AttributedStyle.MAGENTA | AttributedStyle.BRIGHT);
  private static final AttributedStyle H1 = bold(AttributedStyle.YELLOW | AttributedStyle.BRIGHT);
  private static final AttributedStyle H2 = bold(AttributedStyle.CYAN | AttributedStyle.BRIGHT);
  private static final AttributedStyle H3 = bold(AttributedStyle.CYAN);

  // Structural chrome
  private static final AttributedStyle CHROME =
      AttributedStyle.DEFAULT.foreground(
          AttributedStyle.BLACK | AttributedStyle.BRIGHT); // dark gray
  private static final AttributedStyle AGENT_LABEL =
      bold(AttributedStyle.GREEN | AttributedStyle.BRIGHT);
  private static final AttributedStyle PROMPT_STYLE =
      bold(AttributedStyle.CYAN | AttributedStyle.BRIGHT);

  // Lists
  private static final AttributedStyle BULLET =
      bold(AttributedStyle.GREEN | AttributedStyle.BRIGHT);
  private static final AttributedStyle SUB_BULLET =
      AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN);

  // Inline
  private static final AttributedStyle BOLD_SPAN = AttributedStyle.DEFAULT.bold();
  private static final AttributedStyle ITALIC_SPAN = AttributedStyle.DEFAULT.italic();
  private static final AttributedStyle CODE_SPAN =
      AttributedStyle.DEFAULT.foreground(208).background(236); // orange on dark

  // Status
  private static final AttributedStyle INFO_STYLE =
      AttributedStyle.DEFAULT.foreground(AttributedStyle.CYAN | AttributedStyle.BRIGHT);
  private static final AttributedStyle THINK_STYLE =
      AttributedStyle.DEFAULT.italic().foreground(AttributedStyle.BLACK | AttributedStyle.BRIGHT);
  private static final AttributedStyle ERROR_STYLE =
      bold(AttributedStyle.RED | AttributedStyle.BRIGHT);

  // Blockquote
  private static final AttributedStyle BQUOTE_STYLE =
      AttributedStyle.DEFAULT.italic().foreground(AttributedStyle.WHITE);

  // ── Widths ─────────────────────────────────────────────────────────────────
  private static final int BANNER_W = 52;
  private static final int RULE_W = 50;

  // ── Inline Markdown pattern (groups in declaration order) ─────────────────
  // 1: **bold**   2: __bold__   3: *italic*   4: _italic_   5: `code`
  private static final Pattern INLINE_PAT =
      Pattern.compile(
          "\\*\\*(.+?)\\*\\*" // 1 **bold**
              + "|__(.+?)__" // 2 __bold__
              + "|(?<![*])\\*(?![*])(.+?)(?<![*])\\*(?![*])" // 3 *italic*
              + "|(?<![\\w_])_(?!_)(.+?)(?<!_)_(?![\\w_])" // 4 _italic_
              + "|`(.+?)`" // 5 `code`
          );

  private ConsoleRenderer() {}

  // ── Public API ─────────────────────────────────────────────────────────────

  /** Prints the application welcome banner. */
  public static void printBanner() {
    String bar = "═".repeat(BANNER_W);
    String title = "✨   Employee Agent  AI   ✨";
    System.out.println();
    System.out.println(s(BANNER, "  ╔" + bar + "╗"));
    System.out.println(s(BANNER, "  ║" + center(title, BANNER_W) + "║"));
    System.out.println(s(BANNER, "  ╚" + bar + "╝"));
    System.out.println();
  }

  /** Prints the "You ❯" input prompt (no trailing newline). */
  public static void printPrompt() {
    System.out.print(s(PROMPT_STYLE, "  You ❯ "));
  }

  /**
   * Prints an agent reply, rendering any Markdown to coloured ANSI output.
   *
   * @param text raw text or Markdown returned by the AI agent
   */
  public static void agent(String text) {
    System.out.println();
    System.out.println(s(AGENT_LABEL, "  📖  Employee Agent"));
    System.out.println(s(CHROME, "  " + "─".repeat(RULE_W)));
    System.out.println(renderMarkdown(text));
  }

  /** Prints an informational line. */
  public static void info(String text) {
    System.out.println(s(INFO_STYLE, "  ℹ  " + text));
  }

  /** Prints the "Thinking…" spinner line. */
  public static void thinking() {
    System.out.println();
    System.out.println(s(THINK_STYLE, "  ⏳  Thinking…"));
    System.out.println();
  }

  /** Prints an error line. */
  public static void error(String text) {
    System.out.println(s(ERROR_STYLE, "  ✖  " + text));
  }

  // ── Markdown → ANSI ───────────────────────────────────────────────────────

  /**
   * Converts Markdown text to ANSI-coloured terminal output.
   *
   * <p>Supported constructs: {@code #} H1–H3, {@code ---} rules, {@code -&#47;*&#47;+} bullets,
   * {@code 1.} ordered lists, {@code >} blockquotes, {@code **bold**}, {@code *italic*}, and {@code
   * `code`}. Unrecognised lines are printed verbatim.
   */
  public static String renderMarkdown(String text) {
    if (text == null || text.isBlank()) return "";

    StringBuilder out = new StringBuilder();
    boolean prevBlank = false;

    for (String line : text.split("\n", -1)) {
      String rendered = renderLine(line);
      if (rendered.isEmpty()) {
        if (!prevBlank) out.append('\n');
        prevBlank = true;
      } else {
        out.append(rendered).append('\n');
        prevBlank = false;
      }
    }
    return out.toString();
  }

  // ── Line rendering ─────────────────────────────────────────────────────────

  private static String renderLine(String line) {

    // ── Headings ──────────────────────────────────────────────────────────
    if (line.startsWith("# ")) {
      return s(H1, "  " + line.substring(2).trim().toUpperCase());
    }
    if (line.startsWith("## ")) {
      return s(H2, "  ◆ " + line.substring(3).trim());
    }
    if (line.startsWith("### ")) {
      return s(H3, "  ▸ " + line.substring(4).trim());
    }

    // ── Horizontal rule ───────────────────────────────────────────────────
    if (line.matches("^[-─═*]{3,}\\s*$")) {
      return s(CHROME, "  " + "─".repeat(RULE_W));
    }

    // ── Lists ─────────────────────────────────────────────────────────────
    if (line.matches("^  [\\-*+] .+")) { // indented bullet
      return s(SUB_BULLET, "      ◦ ") + inline(line.substring(4).trim());
    }
    if (line.matches("^[\\-*+] .+")) { // top-level bullet
      return s(BULLET, "  • ") + inline(line.substring(2).trim());
    }
    if (line.matches("^\\d+\\.\\s.+")) { // ordered
      int dot = line.indexOf(". ");
      String num = line.substring(0, dot + 1);
      return s(BULLET, "  " + num + " ") + inline(line.substring(dot + 2).trim());
    }

    // ── Blockquote ────────────────────────────────────────────────────────
    if (line.startsWith("> ")) {
      return s(CHROME, "  │ ") + s(BQUOTE_STYLE, line.substring(2).trim());
    }

    // ── Empty ─────────────────────────────────────────────────────────────
    if (line.isBlank()) return "";

    // ── Normal text ───────────────────────────────────────────────────────
    return "  " + inline(line);
  }

  // ── Inline Markdown ────────────────────────────────────────────────────────

  /**
   * Applies inline Markdown spans to a raw text string. Bold is matched before italic to avoid
   * partial `**` collisions.
   */
  private static String inline(String text) {
    StringBuilder sb = new StringBuilder();
    Matcher m = INLINE_PAT.matcher(text);
    int cursor = 0;

    while (m.find()) {
      sb.append(text, cursor, m.start()); // literal text before span

      if (m.group(1) != null) sb.append(s(BOLD_SPAN, m.group(1))); // **bold**
      else if (m.group(2) != null) sb.append(s(BOLD_SPAN, m.group(2))); // __bold__
      else if (m.group(3) != null) sb.append(s(ITALIC_SPAN, m.group(3))); // *italic*
      else if (m.group(4) != null) sb.append(s(ITALIC_SPAN, m.group(4))); // _italic_
      else if (m.group(5) != null) sb.append(s(CODE_SPAN, " " + m.group(5) + " ")); // `code`

      cursor = m.end();
    }
    sb.append(text.substring(cursor)); // trailing literal text
    return sb.toString();
  }

  // ── Helpers ────────────────────────────────────────────────────────────────

  /** Builds a styled ANSI string: applies {@code style}, appends {@code text}, then resets. */
  private static String s(AttributedStyle style, String text) {
    return new AttributedStringBuilder().style(style).append(text).style(RESET).toAnsi();
  }

  /** Convenience: DEFAULT foreground + bold. */
  private static AttributedStyle bold(int color) {
    return AttributedStyle.DEFAULT.bold().foreground(color);
  }

  /** Centers {@code text} within a field of {@code width} characters. */
  private static String center(String text, int width) {
    int pad = width - text.length();
    int left = Math.max(0, pad / 2);
    int right = Math.max(0, pad - left);
    return " ".repeat(left) + text + " ".repeat(right);
  }
}
