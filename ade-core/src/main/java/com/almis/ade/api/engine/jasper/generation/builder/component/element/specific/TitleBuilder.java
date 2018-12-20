package com.almis.ade.api.engine.jasper.generation.builder.component.element.specific;

import com.almis.ade.api.bean.component.Image;
import com.almis.ade.api.bean.section.Title;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 *
 * @author dfuentes
 */
public class TitleBuilder extends ElementBuilder<Title, ComponentBuilder> {

  /**
   *
   * @param element
   * @param reportBuilder
   * @return
   */
  @Override
  @SuppressWarnings("unchecked")
  public ComponentBuilder build(Title element, JasperReportBuilder reportBuilder) {

    HorizontalListBuilder horizontalFlowListBuilder = cmp.horizontalFlowList();

    horizontalFlowListBuilder.add((ComponentBuilder) getBuilderMapper()
                    .getBuilder(element.getLogo().getClass())
                    .build(element.getLogo().setSize(Image.Size.MEDIUM.getSize()), reportBuilder));

    horizontalFlowListBuilder.add(((ComponentBuilder) getBuilderMapper()
                    .getBuilder(element.getTitle().getClass())
                    .build(element.getTitle(), reportBuilder))
                    .setStyle(stl.style()
                                    .setBold(true)
                                    .setFontSize(18)
                                    .setUnderline(true)
                                    .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                                    .setVerticalTextAlignment(VerticalTextAlignment.MIDDLE)));

    horizontalFlowListBuilder.add(((ComponentBuilder) getBuilderMapper()
                    .getBuilder(element.getDate().getClass())
                    .build(element.getDate(), reportBuilder))
                    .setStyle(stl.style()
                                    .setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT)
                                    .setVerticalTextAlignment(VerticalTextAlignment.TOP)));

    return horizontalFlowListBuilder;
  }


}
