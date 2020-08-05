    ArrayList<BusinessTypeDto> businessTypeDtos = new ArrayList<>();
    for (BusinessType businessType : BusinessType.values()) {
      BusinessTypeDto businessTypeDto = new BusinessTypeDto(businessType.getCode(), businessType.getDescription(), businessType.getCompositeBusinessType(), businessType.getConsumptionDiamondOnce(), businessType.getFootballConfigName(), "");
      businessTypeDtos.add(businessTypeDto);
    }
    String content = JSON.toJSONString(businessTypeDtos, true);
    List<BusinessTypeDto> businessTypeDtoConfig = JSON.parseArray(content, BusinessTypeDto.class);
    System.out.println(content);