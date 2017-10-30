package com.company;

public class Scratch {
    private int tonerLevel = 100;
    private int pagesPerTonerLevel = 50;

    private int pagesLoaded = 0;
    private int maxPagesLoaded = 5000;
    private int totalPagesPrinted = 0;
    private int duplexFactor = 1;

    private boolean isDuplex = false;
    private boolean outOfSupplies;



    public Scratch(boolean duplex, int pagesLoaded) {
        this.isDuplex = duplex;

        if(isDuplex){
            this.duplexFactor = 2;
        } else {
            this.duplexFactor = 1;
        }


        if(pagesLoaded <= this.maxPagesLoaded){
            this.pagesLoaded = pagesLoaded;
        } else {
            this.pagesLoaded = this.maxPagesLoaded;
        }

        System.out.println("Duplex printer: " + this.isDuplex);
        System.out.println("Paper loaded: " + this.pagesLoaded);
        System.out.println("Toner level: " + this.tonerLevel);
        System.out.println("Page counter: " + this.totalPagesPrinted);
        System.out.println("*****");
        System.out.println("");
        //this.paperLevel = this.pagesLoaded/this.maxPagesLoaded;
    }

    public void printer(int pages){
        int printPageRequest = pages;
        int printedPages = printPages(printPageRequest);

        updatePageCount( printedPages );
        updateSupplyLevels( printedPages );
        printLowSupplyWarnings();

    }

    private int printLimit(int printRequest, int tonerLimit, int paperLimit){

     /* int printRequest = printRequest;
        int tonerLimit = tonerLimit;
        int paperLimit = paperLimit; */

     if( (printRequest <= tonerLimit)  && (printRequest <= paperLimit) ){
            return printRequest;
        } else if (tonerLimit < paperLimit){
            return tonerLimit;
        } else {
            return paperLimit;
        }
    }

    public void refillPaper (int amountLoaded){
        System.out.println("Current pages loaded: " + this.pagesLoaded);
        System.out.println("Loading paper.");
        this.pagesLoaded += amountLoaded;
        if(this.pagesLoaded > this.maxPagesLoaded){
            this.pagesLoaded = maxPagesLoaded;
        }

        System.out.println("Pages loaded: " + this.pagesLoaded);
    }

    public int printPages (int printPageRequest) {
        System.out.println("");
        System.out.println("Attempting to print " + printPageRequest + " pages...");

        // initialize variables for limits and bottlenecks
        int tonerLimit = tonerToPagesConversion(this.tonerLevel);
        int paperLimit;
        int pagesToPrint;

        // double page limits if printer is duplex
        if(this.isDuplex){
            paperLimit = this.pagesLoaded * 2;
        } else {
            paperLimit = this.pagesLoaded;
        }

        // determine how many pages can be printed
        pagesToPrint = printLimit(printPageRequest, tonerLimit, paperLimit);

        // print number of pages that supplies allow
        if ( (pagesToPrint < tonerLimit) && (pagesToPrint < paperLimit) ) {
            System.out.println("Printing " + pagesToPrint + " pages.");
        } else {
            outOfSuppliesWarning(pagesToPrint, tonerLimit, paperLimit);
            System.out.println("Only printing " + pagesToPrint + " pages.");
        }

        return pagesToPrint;
    }

    private void updateSupplyLevels( int pagesPrinted) {
        //updated supply levels
        this.tonerLevel -= pagesPrinted / this.pagesPerTonerLevel;
        this.pagesLoaded -= pagesPrinted / this.duplexFactor;

        System.out.println("Toner level: " + this.tonerLevel);
        System.out.println("Pages loaded: " + this.pagesLoaded);
        //System.out.println("");
    }

    private void updatePageCount (int pagesPrinted){
        this.totalPagesPrinted += pagesPrinted / this.duplexFactor;
        System.out.println("Page counter: " + this.totalPagesPrinted);

    }

    public void printLowSupplyWarnings (){    //Warnings

        if(this.tonerLevel == 0){
            System.out.println("WARNING: Toner is empty.");
        } else if (this.tonerLevel < 10) {
            System.out.println("Warning: low on toner.");
        }


        if(this.pagesLoaded == 0){
            System.out.println("WARNING: Out of Paper");
        } else if (this.pagesLoaded < 400) {
            System.out.println("Warning: low on paper.");
        }

        return;
    }

    private int tonerToPagesConversion(int tonerLevel){
        int pagesPerLevel = this.pagesPerTonerLevel;

        return tonerLevel * pagesPerLevel;

    }

    private void outOfSuppliesWarning(int pagesPrinted, int tonerLimit, int paperLimit){
        if(pagesPrinted == tonerLimit){
            System.out.println("Running out of ink.");
            this.outOfSupplies = true;
        }

        if(pagesPrinted == paperLimit){
            System.out.println("Running out of paper.");
            this.outOfSupplies = true;
        }

        this.outOfSupplies = false;

    }

    public void fillUpToner(int percentToner){
        System.out.println("Toner level at " + this.tonerLevel + ".");
        System.out.println("Filling up with " + percentToner + " toner.");
        this.tonerLevel += percentToner;
        if(this.tonerLevel > 100){
            this.tonerLevel = 100;
        }

        System.out.println("Toner level now at " + this.tonerLevel + ".");
    }

}
