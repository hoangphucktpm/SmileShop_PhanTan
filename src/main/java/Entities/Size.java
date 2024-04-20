package Entities;

public enum Size {
    s("s"),
    m("m"),
    l("l"), xl("xl"), xxl("xxl"), XXXl("XXXl"), FREE_SIZE("FREE SIZE");
    public String nSiz;
    private Size (String nSiz)
    {
        this.nSiz = nSiz;
    }
}
