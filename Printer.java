package com.company;

public class Printer{

    private double tonerLevel = 100;
    private double paperLevel = 0;
    private int pagesLoaded;
    private int pagesPrinted = 0;
    private boolean duplex;
    private int printFactor = 1;
    private int maxPagesLoaded = 5000;

    public Printer(boolean duplex, int pagesLoaded) {
        this.duplex = duplex;

        if(duplex){
            printFactor = 2;
        } else {
            printFactor = 1;
        }

        if(pagesLoaded <= this.maxPagesLoaded){
            this.pagesLoaded = pagesLoaded;
        } else {
            this.pagesLoaded = this.maxPagesLoaded;
        }

        this.paperLevel = this.pagesLoaded/this.maxPagesLoaded;
    }

    public void fillUpToner(int percentToner){
        System.out.println("Toner level at" + this.tonerLevel +".");
        System.out.println("Filling up toner.");
        this.tonerLevel += percentToner;
        if(this.tonerLevel > 100){
            this.tonerLevel = 100;
        }
        System.out.println("Toner level now at "+ this.tonerLevel);
    }

    public void printPages( int pagesSentToPrint){
        int pagesThatCanBePrinted = (int) this.tonerLevel * 100;
        int paperNeeded = pagesSentToPrint/(this.printFactor);

        System.out.println("");
        System.out.println("Pages that can be printed: " + pagesThatCanBePrinted);
        System.out.println("Paper needed: " + paperNeeded);

        if(pagesThatCanBePrinted == 0){
            System.out.println("Cannot print. Out of ink");
            return;
        }

        if(this.pagesLoaded == 0){
            System.out.println("Cannot print. Out of paper");
            return;
        }
        if(pagesSentToPrint <= pagesThatCanBePrinted && pagesSentToPrint <= this.pagesLoaded){
            this.pagesPrinted += pagesSentToPrint;
            System.out.println("Printing " + pagesSentToPrint +".");
            this.tonerLevel -= pagesSentToPrint/100;
            this.pagesLoaded -= pagesSentToPrint;

        } else if(pagesSentToPrint >= pagesThatCanBePrinted){
            System.out.println("Only printing "+ pagesThatCanBePrinted +" pages. Out of ink.");
            this.pagesPrinted += pagesThatCanBePrinted;
            this.tonerLevel -= pagesThatCanBePrinted/100;
            this.pagesLoaded -= pagesThatCanBePrinted;

        } else if(pagesSentToPrint >= this.pagesLoaded) {
            System.out.println("Only printing " + this.pagesLoaded + " pages. Out of paper.");
            this.pagesPrinted += this.pagesLoaded;
            this.tonerLevel -= this.pagesLoaded / 100;
            this.pagesLoaded -= this.pagesLoaded;
        }

        this.paperLevel = this.pagesLoaded/this.maxPagesLoaded;

        if( (this.tonerLevel * 100) <= 1_000){
            System.out.println("Warning: LOW INK.");
        }

        if(this.pagesLoaded <= (.10 * this.maxPagesLoaded) ){
            System.out.println("Warning: LOW PAPER." + this.pagesLoaded + " pages remaining.");
        }

            System.out.println("Toner level: " + this.tonerLevel + " (" + this.tonerLevel * 100 + " pages)");
            System.out.println("Paper level: " + this.paperLevel + " (" + this.pagesLoaded + " pages)");
    }

    public double getTonerLevel() {
        System.out.println("Toner level: " + tonerLevel);
        return tonerLevel;
    }

    public double getPaperLevel() {
        System.out.println("Paper level: " + paperLevel);
        return paperLevel;
    }

    public int getPagesLoaded() {
        System.out.println("Pages loaded: " + pagesLoaded);
        return pagesLoaded;
    }

    public int getPagesPrinted() {
        System.out.println("Pages printed: " + pagesPrinted);
        return pagesPrinted;
    }

    public boolean isDuplex() {
        System.out.println("Duplex: " + duplex);
        return duplex;
    }

    public int getPrintFactor() {
        System.out.println("Print Factor: " + printFactor);
        return printFactor;
    }

    public int getMaxPagesLoaded() {
        System.out.println("Max Paper Capacity: " + maxPagesLoaded);
        return maxPagesLoaded;
    }
}
