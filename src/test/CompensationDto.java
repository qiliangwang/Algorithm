package test;

/**
 * Created by ayq on 2019-04-09
 */
public class CompensationDto extends Compensation {
  private String nickName;
  private String itemName;
  private String avatar;

  public String getNickName() {
    return nickName;
  }

  public CompensationDto setNickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  public String getItemName() {
    return itemName;
  }

  public CompensationDto setItemName(String itemName) {
    this.itemName = itemName;
    return this;
  }

  public String getAvatar() {
    return avatar;
  }

  public CompensationDto setAvatar(String avatar) {
    this.avatar = avatar;
    return this;
  }
}
