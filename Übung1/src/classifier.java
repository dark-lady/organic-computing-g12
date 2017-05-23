import java.util.HashMap;


public class classifier {


	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private double error;
	private double fitness;
	private int action; // 5 Aktionen: attack(0), move x+16(1) , move x-16.(2) ,move y+16(3), move y-16(4) 
	private double prediction;
	private double beta; //learning rate
	private  double alpha; // kostant
	private double v; // Konstant
	private double e0;//target Error Level Ziel Wert der zur erreichenden Fehlerrate muss evtl angepasst werden 
	private double kj; //
	
	
	// Vollständige Parametrisierung des classifiers
	// x1 und x2 sowie y1 und y2 kennzeichnen die Intervale die der Vulture wahrnimmt 
	//x1=XposVulture -8
	//x2=XposVulture +8
	//y1=YposVultur -8
	//y2=YposVultur -8
	// Muss beim Anlegen des Classifiers beachtet werden !!!! 
	
	
	public classifier(int x1,int y1, int x2, int y2, double error,double fitness,int prediction,int action){  // Volle Parametrisierung des classifiers
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.error=error;
		this.e0=0.2; // Ziel Wert der zur erreichenden Fehlerrate 
		this.fitness=fitness;
		this.prediction=prediction;
		this.alpha=0.1;
		this.beta=0.2;
		this.v=0.5;
		this.action=action;
		this.kj=this.alpha*(Math.pow(error/e0, v*-1));
	}
	
	public classifier(int x1,int y1, int x2, int y2, int action) //Default Konstruktr wird genutz um einen Classifier zu initialisiern, Aktion ist intial zufällig, alerdings kein Agriff initial möglich 
	{
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.prediction=10;
		this.error=0.0;
		this.e0=0.2; // Ziel Wert der zur erreichenden Fehlerrate 
		this.fitness=0.01;
		this.alpha=0.1;
		this.beta=0.2;
		this.v=0.5;
		this.kj=this.alpha*(Math.pow(error/e0, v*-1));
		this.action=action;

	}
	
	
public int getX1(){
	return this.x1;
}
public int getY1(){
	return this.y1;
}

public int getX2(){
	return this.x2;
}
public int getY2(){
	return this.y2;
}


public double getError(){
	return this.error;
}

public double getFitness(){
	return this.fitness;
}

public double getPrediction(){
	return this.prediction;
}

public double getAlpha(){
	return this.alpha;
}

public double getBeta(){
	return this.beta;
}

public double getV(){
	return this.v;
}

public int getAction(){
	return this.action;
}

public void setX1(int x){
	this.x1=x;
}

public void setY1(int y){
	this.y1=y;
}

public void setX2(int x){
	this.x2=x;
}

public void setY2(int y){
	this.y2=y;
}

public void setError(double error){
	this.error=error;
}
public void setFitness(double fitness){
	this.fitness=fitness;
}

public void setPredictions(double predcition){
	this.prediction=prediction;
}

public void setAction(int action){
	this.action=action;
}

public void setAlpha(double alpha){
	this.alpha=alpha;
}

public void setBeta(double beta){
	this.beta=beta;
}

public void setV(double v){
	this.v=v;
}


public boolean checkAction(int action){
	
	if(action == 1 || action==0 )
	{
	return true;
	}
	else
	return false;
}



public boolean isinInterval(int x, int y){  //Überprüft ob eine x und y Koordinate im Bereich eines Classifiers liegt
	
	if((this.x1 < x && x <this.x2) && (this.y1 <y && y<this.y2) )
	{
	return true;
	}
	else
	return false;
}


public void updateFitness(double cliK){ // Fitness aktualisieren summe der Kj für das Aktion Set in der Population 
	double kjnew;
	
	this.kj=this.alpha*(Math.pow(this.error/this.e0, v*-1));
	kjnew=kj/cliK;
	this.fitness=this.fitness + beta * (kjnew-this.fitness);
}

public void updateError(double P){ // Fehlerrate Update P=Reward aus einer Aktion
	this.error=this.error + beta*((Math.abs(P - this.error)) - this.error);
}

public void updatePrediction(double P){ //Prediction aktualisieren P=Reward aus einer Aktion
	this.prediction=this.prediction+beta*(Math.abs(P - this.prediction));
	
}

public double calculatePredictionArrayValue(HashMap<String, classifier> MatchSet, int Action){ //Berechnet den Wert für 
	double prediction; 
	double FitnessSumme=0;
	double GewichtFitnessSumme=0;
	
	for (String p : MatchSet.keySet()){
		
		if(MatchSet.get(p).getAction()==Action){
			FitnessSumme=FitnessSumme+MatchSet.get(p).getFitness();
			GewichtFitnessSumme=GewichtFitnessSumme+(MatchSet.get(p).getFitness()*MatchSet.get(p).getPrediction());
		}
	}
	
	return prediction=GewichtFitnessSumme/FitnessSumme;
	
	
}








	
}
