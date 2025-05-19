public class Position {
    private boolean isMaxTurn;
    private boolean[][] bordMax;
    private boolean[][] bordMin;
    private boolean[][] bordWhole;

    public boolean isMaxTurn() { return isMaxTurn; }
    public void setMaxTurn(boolean isMaxTurn) { this.isMaxTurn = isMaxTurn; }

    public boolean[][] getBordMax() { return bordMax; }
    public void setBordMax(boolean[][] bordMax) { this.bordMax = bordMax; }

    public boolean[][] getBordMin() { return bordMin; }
    public void setBordMin(boolean[][] bordMin) { this.bordMin = bordMin; }

    public boolean[][] getBordWhole() { return bordWhole; }
    public void setBordWhole(boolean[][] bordWhole) { this.bordWhole = bordWhole; }

    public Position(boolean isMaxTurn, boolean[][] bordMax, boolean[][] bordMin, boolean[][] bordWhole) {
        if (bordMax == null || bordMin == null || bordWhole == null) {
            throw new IllegalArgumentException("Board arrays must not be null");
        }
        if (bordMax.length != Main.COLUMNS || bordMin.length != Main.COLUMNS || bordWhole.length != Main.COLUMNS) {
            throw new IllegalArgumentException("Board arrays must have " + Main.COLUMNS + " columns");
        }
        for (int i = 0; i < Main.COLUMNS; i++) {
            if (bordMax[i].length != Main.ROWS || bordMin[i].length != Main.ROWS || bordWhole[i].length != Main.ROWS) {
                throw new IllegalArgumentException("Each board row must have " + Main.ROWS + " rows");
            }
        }
        this.isMaxTurn = isMaxTurn;
        this.bordMax = bordMax;
        this.bordMin = bordMin;
        this.bordWhole = bordWhole;
    }
    
    public Position(){
        isMaxTurn = true;
        bordMax = new boolean[Main.COLUMNS][Main.ROWS];
        bordMin = new boolean[Main.COLUMNS][Main.ROWS];
        bordWhole = new boolean[Main.COLUMNS][Main.ROWS];
        setWhole();
    }
    
    private void setWhole(){
        for (int i = 0; i < bordWhole.length; i++) {
            for (int j = 0; j < bordWhole[0].length; j++) {
                bordWhole[i][j] = bordMax[i][j] | bordMin[i][j];
            }
        }
    }
    
    public boolean isTerminal() {
        for (int i = 0; i < bordMax.length; i++) {
            for (int j = 0; j < bordMax[0].length; j++) {
                if (bordWhole[i][j] && checkWinAt(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkWinAt(int i, int j) {
        for (int k = 0; k < 4; k++) {
            if (Main.directions[i][j][k]) {
                boolean win = true;
                for (int l = 1; l < Main.WINNING_LENGTH; l++) {
                    int x = i + Main.INDICES[k][0] * l;
                    int y = j + Main.INDICES[k][1] * l;
                    if (!bordWhole[x][y] || bordMax[i][j] != bordMax[x][y]) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }
        return false;
    }
}