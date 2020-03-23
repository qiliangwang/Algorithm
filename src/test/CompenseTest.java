package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangql
 * @since 2020-02-19 16:35
 */
public class CompenseTest {

    private static List<CompensationDto> assembleDto(List<Compensation> compensationList,
                                                     Map<Long, String> userInfoMap, Map<Integer, PackageItem> packageMap) {

        List<CompensationDto> dtoList = new ArrayList<>(20);

        for(Compensation compensation: compensationList) {
            String nickName = "";
////            BasicUserInfo userInfo = userInfoMap.get(compensation.getUid());
//            if (userInfo != null) {
//                nickName = userInfo.getNickname();
//            }
//
//            String itemName;
//            String avatar = null;
//
//            if(compensation.getType() == LotteryItemType.VIRTUAL_CURRENCY.getCode()) {
//                itemName = AccountType.findByCode(compensation.getItemId()).getName();
//            } else {
//                Integer packageItemId = compensation.getItemId();
//                itemName = packageMap.get(packageItemId).getName();
//                avatar = packageMap.get(packageItemId).getAvatar();
//            }
//
//            CompensationDto dto = CompensationConverter.convertToDto(compensation, nickName, itemName, avatar);
            dtoList.add(null);
        }

        return dtoList;
    }

    public static void main(String[] args) {
        List<Compensation> compensationList = null;

        assembleDto(new ArrayList<>(), null, null);
    }
}
