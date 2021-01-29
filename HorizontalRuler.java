public class HorizontalRuler {

    // Depth is iterative, horizontal spacing is recursive
    public static void drawRuler(int nInches, int majorLength) {
        for (int i = majorLength; i >= 0; i--) {
            drawLine(nInches, i, majorLength);
        }
    }
    
    private static void drawLine(int nInches, int curLine, int tickLength) {
        if (curLine > 0)
            drawVerticalTick();
        else
            drawLabel(0);

        for (int i = 1; i <= nInches; i++) {
            drawHorzInterval(curLine, tickLength - 1);
            if (curLine > 0)
                drawVerticalTick();
            else
                drawLabel(i);
        }

        System.out.println();
    }

    private static void drawHorzInterval(int curLine, int currentTickLength) {
        if (currentTickLength > 0) {
            drawHorzInterval(curLine, currentTickLength - 1);

            // General case and exception case respectively
            if (curLine > 0 && (currentTickLength % curLine == 0 || curLine == currentTickLength - 1))
                drawVerticalTick();
            else
                drawVerticalSpace();

            drawHorzInterval(curLine, currentTickLength - 1);
        }
    }

    private static void drawVerticalTick() {
        System.out.print("|  ");
    }

    private static void drawVerticalSpace() {
        System.out.print("   ");
    }

    private static void drawLabel(int i) {
        System.out.print(i + "  ");
    }

    public static void main(String[] args) {
        System.out.println("Horizontal Ruler - Azeb Ayalneh, Matthew Chesser, Jeffrey Knutsen, Cong Qin");
		System.out.print("\n\nRuler of length 2 with major tick length 4\n");
		drawRuler(2, 4);
		System.out.print("\n\nRuler of length 1 with major tick length 5\n");
		drawRuler(1, 5);
		System.out.print("\n\nRuler of length 3 with major tick length 3\n");
		drawRuler(3, 3);
	}
}
