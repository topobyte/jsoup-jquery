// Copyright 2019 Sebastian Kuerten
//
// This file is part of jsoup-jquery.
//
// jsoup-jquery is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// jsoup-jquery is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with jsoup-jquery. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.jsoup.jquery;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Document;

import de.topobyte.jsoup.ElementUtil;
import de.topobyte.jsoup.HTML;
import de.topobyte.jsoup.HtmlBuilder;
import de.topobyte.jsoup.components.Form;
import de.topobyte.jsoup.components.Input;
import de.topobyte.jsoup.components.Input.Type;
import de.topobyte.jsoup.components.P;
import de.topobyte.jsoup.nodes.Element;

public class Test
{

	public static void main(String[] args) throws IOException
	{
		HtmlBuilder builder = new HtmlBuilder();

		Element head = builder.getHead();

		String header = IOUtils
				.toString(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("jquery.headers.html"));
		System.out.println(header);
		ElementUtil.appendFragment(head, header);

		Element body = builder.getBody();

		body.ac(HTML.h1("JSoup and jQuery"));

		P p = body.ac(HTML.p());
		p.appendText(
				"The input below should be focussed when this page is loaded");

		Form form = body.ac(HTML.form());
		Input input = form.ac(HTML.input());
		input.setType(Type.TEXT);
		input.setId("an-input");

		body.ac(JQuery.focusById("an-input"));

		p = body.ac(HTML.p());
		p.appendText("Lorem ipsum...");

		Document doc = builder.getDocument();
		System.out.println(doc);

		builder.write(new File("/tmp/jsoup-jquery.html"));
	}

}