package com.sample.app.commmands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

import com.sample.app.customizations.ShellOutputHelper;

@ShellComponent
public class TableExamplesCommand {

	public String[] headers = { "Country Name", "Capital" };

	public String[] countries = { "India", "New Delhi" };
	public String[] capitals = { "Japan", "Tokyo" };

	@Autowired
	ShellOutputHelper shellHelper;

	@ShellMethod("Display sample tables")
	public void sampleTables() {
		Object[][] sampleData = new String[][] { headers, countries, capitals };
		TableModel model = new ArrayTableModel(sampleData);
		TableBuilder tableBuilder = new TableBuilder(model);

		shellHelper.printInfo("Air border style");
		tableBuilder.addFullBorder(BorderStyle.air);
		shellHelper.print(tableBuilder.build().render(80));

		shellHelper.printInfo("Oldschool border style");
		tableBuilder.addFullBorder(BorderStyle.oldschool);
		shellHelper.print(tableBuilder.build().render(80));

		shellHelper.printInfo("Fancy_light border style");
		tableBuilder.addFullBorder(BorderStyle.fancy_light);
		shellHelper.print(tableBuilder.build().render(80));

		shellHelper.printInfo("Fancy_double border style");
		tableBuilder.addFullBorder(BorderStyle.fancy_double);
		shellHelper.print(tableBuilder.build().render(80));

		shellHelper.printInfo("ixed border style");
		tableBuilder.addInnerBorder(BorderStyle.fancy_light);
		tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
		shellHelper.print(tableBuilder.build().render(80));
	}

}