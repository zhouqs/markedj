package io.github.gitbucket.markedj;

import static io.github.gitbucket.markedj.Utils.*;

public class Renderer {

	protected Options options;

	public Renderer(Options options) {
		this.options = options;
	}

	public String code(String code, String lang, boolean escaped) {
		StringBuilder sb = new StringBuilder("<pre><code");
		String classString = options.getTagClass("code");
		if (!isEmpty(lang)) {
			classString = classString + options.getLangPrefix() + escape(lang, true);
		}

		if (classString != null) {
			sb.append("class=\"").append(classString).append("\"");
		}

		sb.append(">\n");
		if (escaped) {
			sb.append(code);
		} else {
			sb.append(escape(code, true));
		}
		sb.append("\n</code></pre>\n");
		return sb.toString();
	}

	public String blockquote(String quote) {
		StringBuilder sb = new StringBuilder("<blockquote");
		String classString = options.getTagClass("blockquote");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">\n").append(quote).append("</blockquote>\n");
		return sb.toString();
	}

	public String html(String html) {
		return html;
	}

	public String heading(String text, int level, String raw) {
		StringBuilder sb = new StringBuilder("<h");
		sb.append(level).append(" id=\"").append(options.getHeaderPrefix())
				.append(raw.toLowerCase().replaceAll("[^\\w]+", "-")).append("\"");
		String classString = options.getTagClass("h" + level);
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">").append(text).append("</h").append(level).append(">\n");
		return sb.toString();
	}

	public String hr() {
		StringBuilder sb = new StringBuilder("<hr");
		String classString = options.getTagClass("hr");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">\n");
		return sb.toString();
	}

	public String list(String body, boolean ordered) {
		String listType;
		if (ordered) {
			listType = "ol";
		} else {
			listType = "ul";
		}
		StringBuilder sb = new StringBuilder("<");
		sb.append(listType);
		String classString = options.getTagClass(listType);
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">\n").append(body).append("</").append(listType).append(">\n");
		return sb.toString();
	}

	public String listitem(String text) {
		StringBuilder sb = new StringBuilder("<li");
		String classString = options.getTagClass("li");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">").append(text).append("</li>\n");
		return sb.toString();
	}

	public String paragraph(String text) {
		StringBuilder sb = new StringBuilder("<p");
		String classString = options.getTagClass("p");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">").append(text).append("</p>\n");
		return sb.toString();
	}

	public String table(String header, String body) {
		StringBuilder sb = new StringBuilder("<table");
		String classString = options.getTagClass("table");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">\n<thead>\n").append(header).append("</thead>\n<tbody>\n").append(body)
				.append("</tbody>\n</table>\n");
		return sb.toString();
	}

	public String tablerow(String content) {
		StringBuilder sb = new StringBuilder("<tr");
		String classString = options.getTagClass("tr");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">\n").append(content).append("</tr>\n");
		return sb.toString();
	}

	public String tablecell(String content, TableCellFlags flags) {
		String cellType;
		if (flags.isHeader()) {
			cellType = "th";
		} else {
			cellType = "td";
		}

		StringBuilder sb = new StringBuilder("<");
		sb.append(cellType);
		String classString = options.getTagClass(cellType);
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		String align = flags.getAlign();
		if (align != null) {
			sb.append(" style=\"text-align: ").append(align).append("\"");
		}
		sb.append(">\n").append(content).append("</").append(cellType).append(">\n");
		return sb.toString();
	}

	public String strong(String text) {
		StringBuilder sb = new StringBuilder("<strong");
		String classString = options.getTagClass("strong");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">").append(text).append("</strong>\n");
		return sb.toString();
	}

	public String em(String text) {
		StringBuilder sb = new StringBuilder("<em");
		String classString = options.getTagClass("em");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">").append(text).append("</em>\n");
		return sb.toString();
	}

	public String codespan(String text) {
		StringBuilder sb = new StringBuilder("<code");
		String classString = options.getTagClass("code");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">\n").append(text).append("</code>\n");
		return sb.toString();
	}

	public String br() {
		return "<br>";
	}

	public String del(String text) {
		StringBuilder sb = new StringBuilder("<del");
		String classString = options.getTagClass("del");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		sb.append(">").append(text).append("</del>\n");
		return sb.toString();
	}

	public String link(String href, String title, String text) {
		StringBuilder sb = new StringBuilder("<a href=\"");
		sb.append(href).append("\"");
		String classString = options.getTagClass("a");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		if (title != null) {
			sb.append(" title=\"").append(title).append("\"");
		}
		sb.append(">").append(text).append("</a>");
		return sb.toString();
	}

	public String image(String href, String title, String text) {
		StringBuilder sb = new StringBuilder("<img src=\"");
		sb.append(href).append("\"");
		String classString = options.getTagClass("img");
		if (classString != null) {
			sb.append(" class=\"").append(classString).append("\"");
		}
		if (title != null) {
			sb.append(" title=\"").append(title).append("\"");
		}
		sb.append("\" alt=\"").append(text).append(">");
		return sb.toString();
	}

	public String nolink(String text) {
		return escape(text);
	}

	public String text(String text) {
		return text;
	}

	public static class TableCellFlags {
		private boolean header;
		private String align;

		public TableCellFlags(boolean header, String align) {
			this.header = header;
			this.align = align;
		}

		public boolean isHeader() {
			return header;
		}

		public String getAlign() {
			return align;
		}
	}

}
