package Entities;

public enum Size {
    S("S"),
    M("M"),
    L("L"), XL("XL"), XXL("XXL"), XXXL("XXXL"), FREE_SIZE("FREE_SIZE");
    public String nSiz;

    private Size(String nSiz) {
        this.nSiz = nSiz;
    }
}
