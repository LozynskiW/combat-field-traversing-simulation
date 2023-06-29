import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
class MovementVisualization {

    private String[][] terrainVisualisation;
    private Terrain terrain;
    private List<Unit> unitSet;

    private HashMap<Unit, Character> unitToSymbolsMap = new HashMap<>();;

    private final char[] symbols = new char[] {'x', 'o', '+', '*'};

    public void visualise() {

        this.placeUnitsPastPositionsOnTerrain();

        String row;

        for (int height = 0; height < this.terrainVisualisation.length; height++) {

            row = "";

            for (int weight = 0; weight < this.terrainVisualisation[0].length; weight++) {

                row += this.terrainVisualisation[height][weight];
            }

            System.out.println(row);
        }

    }

    public void placeUnitsPastPositionsOnTerrain() {

        populateUnitToSymbolsMap();
        this.terrainVisualisation = this.initializeTerrain();

        for (int height = 0; height < this.terrainVisualisation.length; height++) {

            for (int width = 0; width < this.terrainVisualisation[0].length; width++) {

                for (Unit unit : this.unitSet) {

                    for (int[] pastPosition: unit.getPastPositions()) {

                        if ((pastPosition[0]==height) && (pastPosition[1]==width)) {

                            this.terrainVisualisation[height][width] = String.valueOf(this.unitToSymbolsMap.get(unit));

                        }

                    }

                }

            }

        }

    }

    private String[][] initializeTerrain() {

        String[][] terrain = new String[this.terrain.getHeight()][this.terrain.getWidth()];

        for (int i = 0; i < this.terrain.getHeight(); i++) {

            Arrays.fill(terrain[i], ".");

        }

        return terrain;

    }

    private void populateUnitToSymbolsMap() {

        int symbolIndex = 0;

        for (Unit unit:this.unitSet) {

            this.unitToSymbolsMap.put(unit, this.symbols[symbolIndex]);
            symbolIndex++;
        }
    }

    public MovementVisualization(Terrain terrain, List<Unit> unitSet) {
        this.terrain = terrain;
        this.unitSet = unitSet;
    }

    public MovementVisualization() {
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public List<Unit> getUnitSet() {
        return unitSet;
    }

    public void setUnitSet(List<Unit> unitSet) {
        this.unitSet = unitSet;
    }

}
