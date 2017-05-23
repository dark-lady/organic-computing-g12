import bwapi.*;
import java.util.HashSet;
import java.util.HashMap;

public class Vulture {

    private final Mirror bwapi;
    private final HashSet<Unit> enemyUnits;
    private HashMap<String, classifier> population;
    final private Unit unit;

    public Vulture(Unit unit, Mirror bwapi, HashSet<Unit> enemyUnits) {
        this.unit = unit;
        this.bwapi = bwapi;
        this.enemyUnits = enemyUnits;
        this.population=new HashMap <String,classifier>();
    }

    public void step() {
        /**
         * TODO: XCS
         */
    	
    	//Population als Hash Map
    	// Wenn kein Classifier vorhanden dann  neuen  hinzufügen
    	// Unterschiedliche Aktion möglich in unterschiedlichen Situationen
    	
    	/*  Testdurchlauf mit festen Werten */
    	this.unit.move(new Position(1500,900));
    	this.unit.move(new Position(1700,900));
    	this.unit.move(new Position(1900,900));
    	
    	if(this.unit.getPosition().getX()==1500 && this.unit.getPosition().getY()==900){
    		population.put("1", new classifier(this.unit.getPosition().getX()-8, this.unit.getPosition().getX()+8, this.unit.getPosition().getY()-8, this.unit.getPosition().getY()+8, 1));
    	}
    	
    	if(this.unit.getPosition().getX()==1700 && this.unit.getPosition().getY()==900){
    		population.put("2", new classifier(this.unit.getPosition().getX()-8, this.unit.getPosition().getX()+8, this.unit.getPosition().getY()-8, this.unit.getPosition().getY()+8, 1));
    	}
    	
    	if(this.unit.getPosition().getX()==1900 && this.unit.getPosition().getY()==900){
    		population.put("3", new classifier(this.unit.getPosition().getX()-8, this.unit.getPosition().getX()+8, this.unit.getPosition().getY()-8, this.unit.getPosition().getY()+8, 1));
    	}
    	
    	
    	for (String p: population.keySet()){
    		System.out.println("X Bereich:" + population.get(p).getX1()+ ","+ population.get(p).getX2()+ "Y-Bereich"  + population.get(p).getY1()+ ","+ population.get(p).getY2());
    	}
    	
    	
    	//Überprüfung ob bestimmte Aktionen schon in einer Situation durchgeführt wurden 
    	//Überürüfung ob für eine Situation (Bereich) überhaupt schon eien AKtion durchegführt wurde
    	//Nach welchen Schema bewegen wir Einheiten?
    	//Welche Situationen können auftreten?
    	//Welche Aktionen können in unterschiedlichen Situatioen durchgeführt werden?
    	
    	
    	
    	
    }

    private void move(Unit target) {
        unit.move(new Position(target.getPosition().getX(), target.getPosition().getY()), false);
    }

    private Unit getClosestEnemy() {
        Unit result = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Unit enemy : enemyUnits) {
            double distance = getDistance(enemy);
            if (distance < minDistance) {
                minDistance = distance;
                result = enemy;
            }
        }

        return result;
    }

    private double getDistance(Unit enemy) {
    	return this.unit.getPosition().getDistance(enemy.getPosition());
    }
}




