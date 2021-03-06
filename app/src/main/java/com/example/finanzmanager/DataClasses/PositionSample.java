package com.example.finanzmanager.DataClasses;


public class   PositionSample implements Comparable{
    private int positionType;
    private Date date;
    private double value;
    private boolean reocurring;
    private String category;
    private String description;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 0 = Expense
     * 1 = Income
     */
    public int getPositionType() {
        return positionType;
    }

    /**
     * 0 = Expense
     * 1 = Income
     * @param positionType
     */
    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    /**
     *
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * set the Date
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * set the value
     * @param value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     *
     * @return true if a Position is recurring
     */
    public boolean getReocurring() {
        return reocurring;
    }

    /**
     * sets the recurring value
     * @param reocurring
     */
    public void setReocurring(boolean reocurring) {
            this.reocurring = reocurring;
    }

    @Override
    public String toString() {
        return "PositionSample{" +
                "positionType=" + positionType +
                ", date=" + date +
                ", value=" + value +
                ", reocurring=" + reocurring +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    /**
     *
     * @returns a String array of the exports
     */
    public String[] readyToExport(){
        String[] s = new String[8];
        s[0] = String.valueOf(positionType);
        s[1] = String.valueOf(date.getDay());
        s[2] = String.valueOf(date.getMonth());
        s[3] = String.valueOf(date.getYear());
        s[4] = String.valueOf(value);
        s[5] = ((Boolean)reocurring).toString();
        s[6] = category;
        s[7] = description;

        return s;
    }


    @Override
    public int compareTo(Object o) {
        if(!(o instanceof PositionSample))
            return -1;
        if(this.date.getYear()> ((PositionSample) o).getDate().getYear()){
            return 1;
        }
        else if(this.date.getYear()< ((PositionSample) o).getDate().getYear()){
            return -1;
        }
        else{
            if(this.date.getMonth()> ((PositionSample) o).getDate().getMonth()){
                return 1;
            }
            else if(this.date.getMonth()< ((PositionSample) o).getDate().getMonth()){
                return -1;
            }
            else{
                if(this.date.getDay()> ((PositionSample) o).getDate().getDay()){
                    return 1;
                }
                else if(this.date.getDay()< ((PositionSample) o).getDate().getDay()){
                    return -1;
                }
                else{
                    return 0;
                }

            }
        }
    }

}
