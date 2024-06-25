package com.devuoon.backboard.common;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component("CommonUtil")
public class CommonUtil {
  public String markdown(String content) {
    Parser parser = Parser.builder().build();
    Node document = parser.parse(content); // 기존 마크다운으로 작성된 글 파싱
    HtmlRenderer renderer = HtmlRenderer.builder().build(); // HTML로 렌더링 텍스트

    return renderer.render(document);
  }
}
