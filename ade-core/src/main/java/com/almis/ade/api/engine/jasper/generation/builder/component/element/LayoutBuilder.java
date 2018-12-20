package com.almis.ade.api.engine.jasper.generation.builder.component.element;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.bean.component.Layout;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.*;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

/**
 *
 * @author dfuentes
 */
public class LayoutBuilder extends ElementBuilder<Layout, ComponentBuilder> {

  /**
   *
   * @param element
   * @param jasperReportBuilder
   * @return
   */
  @Override
  public ComponentBuilder build(Layout element, JasperReportBuilder jasperReportBuilder) {

    DimensionComponentBuilder layoutBuilder;
    if (element.getElements() != null && !element.getElements().isEmpty()) {
      switch (element.getType()) {
        case MULTIPAGE:
          layoutBuilder = getMultipageLayout(element, jasperReportBuilder);
          break;

        case VERTICAL:
          layoutBuilder = getVerticalLayout(element, jasperReportBuilder);
          break;

        case HORIZONTAL:
        default:
          layoutBuilder = getHorizontalLayout(element, jasperReportBuilder);
          break;
      }
    } else {
      layoutBuilder = cmp.verticalList();
    }

    // Set width if defined
    if (element.getWidth() != null) {
      layoutBuilder.setFixedWidth(element.getWidth());
    }

    // Set height if defined
    if (element.getHeight() != null) {
      layoutBuilder.setFixedHeight(element.getHeight());
    }

    return layoutBuilder;
  }

  /**
   * Get multipage layout
   * @param element
   * @param jasperReportBuilder
   * @return
   */
  @SuppressWarnings("unchecked")
  private DimensionComponentBuilder getMultipageLayout(Layout element, JasperReportBuilder jasperReportBuilder) {
    MultiPageListBuilder multiPageListBuilder = cmp.multiPageList()
      .setStyle(element.getStyle());

    for (Element current : element.getElements()) {
      multiPageListBuilder.add((ComponentBuilder) getBuilderMapper()
        .getBuilder(current.getClass())
        .build(current, jasperReportBuilder));
    }

    return multiPageListBuilder;
  }

  /**
   * Get horizontal layout
   * @param element
   * @param jasperReportBuilder
   * @return
   */
  @SuppressWarnings("unchecked")
  private DimensionComponentBuilder getHorizontalLayout(Layout element, JasperReportBuilder jasperReportBuilder) {
    HorizontalListBuilder horizontalList = cmp.horizontalFlowList()
      .setStyle(element.getStyle());

    if (element.getGap() != null) {
      horizontalList.setGap(element.getGap());
    }

    for (Element current : element.getElements()) {
      horizontalList.add((ComponentBuilder) getBuilderMapper()
        .getBuilder(current.getClass())
        .build(current, jasperReportBuilder));
    }

    return horizontalList;
  }

  /**
   * Get vertical layout
   * @param element
   * @param jasperReportBuilder
   * @return
   */
  @SuppressWarnings("unchecked")
  private DimensionComponentBuilder getVerticalLayout(Layout element, JasperReportBuilder jasperReportBuilder) {
    VerticalListBuilder verticalList = cmp.verticalList()
      .setStyle(element.getStyle());

    if (element.getGap() != null) {
      verticalList.setGap(element.getGap());
    }

    for (Element current : element.getElements()) {
      verticalList.add((ComponentBuilder) getBuilderMapper()
        .getBuilder(current.getClass())
        .build(current, jasperReportBuilder));
    }

    return verticalList;
  }
}
