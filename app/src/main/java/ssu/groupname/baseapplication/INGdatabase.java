package ssu.groupname.baseapplication;

public class INGdatabase {
    //fields
    private String ingredent;
    private String EXPdate;
    //constructors
    public INGdatabase()    {}
    public INGdatabase(String EXPdates, String ingredents)
    {
        this.ingredent = ingredents;
        this.EXPdate = EXPdates;
    }
    //properties
    public void setIngredent(String ingredents)
    {
        this.ingredent = ingredents;
    }
    public String getIngredent()
    {
        return this.ingredent;
    }

    public void setEXPdate(String exp)
    {
        this.EXPdate = exp;
    }
    public String getEXPdate()
    {
        return this.EXPdate;
    }




}
