package com.sample.app.console;

/**
 * JLine 3 colour index constants for use with {@link
 * org.jline.utils.AttributedStyle#foreground(int)}.
 *
 * <p>Indices 0–7 map to standard 4-bit ANSI colours ({@code \033[3Xm}). Adding {@code BRIGHT} (8)
 * gives the bright variant ({@code \033[9Xm}). Indices 16–255 use the 256-colour escape ({@code
 * \033[38;5;Xm}).
 */
public final class ConsoleColors {

  public static final int BLACK = 0;
  public static final int RED = 1;
  public static final int GREEN = 2;
  public static final int YELLOW = 3;
  public static final int BLUE = 4;
  public static final int MAGENTA = 5;
  public static final int CYAN = 6;
  public static final int WHITE = 7;

  /** Add to any colour index to get its bright/high-intensity variant. */
  public static final int BRIGHT = 8;

  // Semantic aliases
  public static final int USER = CYAN | BRIGHT; // 14
  public static final int AGENT = GREEN | BRIGHT; // 10
  public static final int INFO = CYAN | BRIGHT; // 14
  public static final int ERROR = RED | BRIGHT; // 9
  public static final int STORY = WHITE; // 7
  public static final int GRAY = BLACK | BRIGHT; // 8  (dark gray)
  public static final int ORANGE = 208; // 256-colour

  private ConsoleColors() {}
}
