package test;

/**
 * @author welsey chen
 */
public class FloatScreenDto {
  public enum Type {
    GIFT_BOX(1), COMBO_GIFT(2), OPEN_NOBLE(3),;
    private int code;

    Type(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }
  }

  private Long lid;
  private Long cid;
  private Long rid;
  private Long uid;
  private String src;
  private String dst;
  private String nn;
  private Integer cnt;
  private String gift;
  private String txt;
  private Boolean skip;
  private transient Integer type;
  private String box;
  private Integer tmpId;

  public Long getLid() {
    return lid;
  }

  public void setLid(Long lid) {
    this.lid = lid;
  }

  public Long getCid() {
    return cid;
  }

  public void setCid(Long cid) {
    this.cid = cid;
  }

  public Long getRid() {
    return rid;
  }

  public void setRid(Long rid) {
    this.rid = rid;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  public String getDst() {
    return dst;
  }

  public void setDst(String dst) {
    this.dst = dst;
  }

  public String getNn() {
    return nn;
  }

  public void setNn(String nn) {
    this.nn = nn;
  }

  public Integer getCnt() {
    return cnt;
  }

  public void setCnt(Integer cnt) {
    this.cnt = cnt;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getGift() {
    return gift;
  }

  public void setGift(String gift) {
    this.gift = gift;
  }

  public String getTxt() {
    return txt;
  }

  public void setTxt(String txt) {
    this.txt = txt;
  }

  public Boolean getSkip() {
    return skip;
  }

  public void setSkip(Boolean skip) {
    this.skip = skip;
  }

  public String getBox() {
    return box;
  }

  public void setBox(String box) {
    this.box = box;
  }

  public Integer getTmpId() {
    return tmpId;
  }

  public void setTmpId(Integer tmpId) {
    this.tmpId = tmpId;
  }
}
