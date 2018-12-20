package com.almis.ade.api.enumerate;

import net.sf.dynamicreports.report.builder.component.ComponentBuilder;

import static net.sf.dynamicreports.report.builder.DynamicReports.bcode;

/**
 * @author dfuentes
 */
public enum BarcodeType {
  CODABAR,
  CODE39,
  CODE39_EXTENDED,
  CODE128,
  CODE128A,
  CODE128B,
  CODE128C,
  EAN8,
  EAN13,
  EAN128,
  DATA_MATRIX,
  INTERLEAVED_2_OF_5,
  UPCA,
  UPCE,
  UCC128,
  USD3,
  USD4,
  USPS,
  SSCC18,
  SCC14_SHIPPING_CODE,
  USPS_INTELLIGENT_MAIL,
  ROYAL_MAIL_CUSTOMER,
  GLOBAL_TRADE_ITEM_NUMBER,
  RANDOM_WEIGHT_UPCA,
  SHIPMENT_IDENTIFICATION_NUMBER,
  POSTNET,
  PDF417,
  MONARCH,
  NW7,
  INT_2_OF_5,
  TWO_OF_7,
  THREE_OF_9,
  STD_2_OF_5,
  BOOKLAND;

  public ComponentBuilder getBarcode(String code) {
    switch (this) {
      case CODABAR:
        return bcode.codabar(code);

      case CODE39:
        return bcode.code39(code);

      case CODE39_EXTENDED:
        return bcode.barbecue_code39Extended(code);

      case CODE128:
        return bcode.code128(code);

      case CODE128A:
        return bcode.barbecue_code128A(code);

      case CODE128B:
        return bcode.barbecue_code128B(code);

      case CODE128C:
        return bcode.barbecue_code128C(code);

      case EAN8:
        return bcode.ean8(code);

      case EAN13:
        return bcode.ean13(code);

      case EAN128:
        return bcode.ean128(code);

      case DATA_MATRIX:
        return bcode.dataMatrix(code);

      case INTERLEAVED_2_OF_5:
        return bcode.interleaved2Of5(code);

      case UPCA:
        return bcode.upca(code);

      case UPCE:
        return bcode.upce(code);

      case UCC128:
        return bcode.barbecue_ucc128(code);

      case USD3:
        return bcode.barbecue_usd3(code);

      case USD4:
        return bcode.barbecue_usd4(code);

      case USPS:
        return bcode.barbecue_usps(code);

      case SSCC18:
        return bcode.barbecue_sscc18(code);

      case SCC14_SHIPPING_CODE:
        return bcode.barbecue_scc14ShippingCode(code);

      case USPS_INTELLIGENT_MAIL:
        return bcode.uspsIntelligentMail(code);

      case ROYAL_MAIL_CUSTOMER:
        return bcode.royalMailCustomer(code);

      case GLOBAL_TRADE_ITEM_NUMBER:
        return bcode.barbecue_globalTradeItemNumber(code);

      case RANDOM_WEIGHT_UPCA:
        return bcode.barbecue_randomWeightUpca(code);

      case SHIPMENT_IDENTIFICATION_NUMBER:
        return bcode.barbecue_shipmentIdentificationNumber(code);

      case POSTNET:
        return bcode.postnet(code);

      case PDF417:
        return bcode.pdf417(code);

      case MONARCH:
        return bcode.barbecue_monarch(code);

      case NW7:
        return bcode.barbecue_nw7(code);

      case INT_2_OF_5:
        return bcode.barbecue_int2of5(code);

      case TWO_OF_7:
        return bcode.barbecue_2of7(code);

      case THREE_OF_9:
        return bcode.barbecue_3of9(code);

      case STD_2_OF_5:
        return bcode.barbecue_std2of5(code);

      case BOOKLAND:
        return bcode.barbecue_bookland(code);

      default:
        return null;

    }
  }
}
