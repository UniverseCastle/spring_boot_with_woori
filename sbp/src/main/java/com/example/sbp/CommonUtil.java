package com.example.sbp;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	public String markdown(String markdown) {		// commonMark~ 임포트
		Parser parser = Parser.builder().build();
		Node document = parser.parse(markdown);		// commonMark~ 임포트
		HtmlRenderer renderer = HtmlRenderer.builder().build();
		
		return renderer.render(document);
	}
}