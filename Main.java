package com.company;

public class Main {

    public static void main(String[] args) {
	/*Printer printer = new Printer(false,600);

	printer.getMaxPagesLoaded();
	printer.getPagesLoaded();
	printer.getPagesPrinted();
	printer.getPaperLevel();
	printer.getTonerLevel();
	printer.getPrintFactor();

	printer.printPages(100);

	System.out.println("****");
    printer.getPagesLoaded();
    printer.getPagesPrinted();
    printer.getPaperLevel();
    printer.getTonerLevel();
    printer.printPages(200);

    System.out.println("****");
    printer.getPagesLoaded();
    printer.getPagesPrinted();
    printer.getPaperLevel();
    printer.getTonerLevel();
    printer.printPages(300); */

	Scratch printer = new Scratch(false, 12_000);

	for (int i = 1; i < 8; i++) {

        System.out.println("");
        System.out.println("*************************");
        System.out.println("     Cycle " + i);
        System.out.println("*************************");
        System.out.println("");

        printer.printer(100);
        printer.printer(200);
        printer.printer(300);
        printer.printer(400);
        printer.printer(500);

        System.out.println("");

        if(i == 5){
            printer.fillUpToner( 120);

        }

        if(i == 6){
            printer.refillPaper(2_000);
        }
    }

    }
}
