package applicationModels;

public class ApplicationModelData {

	
	private double[] vehicle_only_criteria;
	private double[] elastic_criteria;
	private double[] fall_back_criteria;
	private double[] duplicate_criteria;
	private double[] parallel_criteria;
	private double[] cloud_edge_only_criteria;
	
	private double[] importance_factor;
	
	
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	

	public ApplicationModelData(){
		//Initialize Criteria of the Application Models itself (a.k.a. Thresholds of the application models!!!)
		vehicle_only_criteria = new double[ApplicationDecision.getNrofcriteria()];
		elastic_criteria = new double[ApplicationDecision.getNrofcriteria()];
		fall_back_criteria = new double[ApplicationDecision.getNrofcriteria()];
		duplicate_criteria = new double[ApplicationDecision.getNrofcriteria()];
		parallel_criteria = new double[ApplicationDecision.getNrofcriteria()];
		cloud_edge_only_criteria = new double[ApplicationDecision.getNrofcriteria()];
	
		for(int i=0; i<ApplicationDecision.getNrofcriteria(); i++) {	
			vehicle_only_criteria[i] = 0.0;
	    	elastic_criteria[i] = 0.0;
	    	fall_back_criteria[i] = 0.0;
	    	duplicate_criteria[i] = 0.0;
	    	parallel_criteria[i] = 0.0;
	    	cloud_edge_only_criteria[i] = 0.0;
		}
		

		//Importance_factors for each of the criteria
		importance_factor = new double[ApplicationDecision.getNrofcriteria()];
		
    	//-----------------------------------------------------------------------------------
    	//-----------------------------------------------------------------------------------
		//--------------Initialization of application model thresholds-----------------------
    	//-----------------------------------------------------------------------------------
    	//-----------------------------------------------------------------------------------
		
		//Convention (after Min/Maxing it!): 
		// 0 	--> no/low threshold --> Easy Points!
		// 1.0 	--> very high threshold --> below: full points, above: scaled! 
		
		//0. Availability/Robustness
		//1. Latency
		//2. Processing Performance
		//3. Energy consumption  of Vehicle
		//5. Necessary On-Board Hardware
		//5. Security 
		//6. Operation Expenses 
		//7. Capital Expenditures			
			
		//STANDARD VALUES, do not have to be in a specific range! (since min-max normalized later!)
		vehicle_only_criteria[0] = 10;
		vehicle_only_criteria[1] = 0;
		vehicle_only_criteria[2] = 100;
		vehicle_only_criteria[3] = 80;
		vehicle_only_criteria[4] = 100;
		vehicle_only_criteria[5] = 0;  
		vehicle_only_criteria[6] = 0;
		vehicle_only_criteria[7] = 50;
		
		elastic_criteria[0] = 80;
		elastic_criteria[1] = 90; 
		elastic_criteria[2] = 0; 
		elastic_criteria[3] = 60;
		elastic_criteria[4] = 100;
		elastic_criteria[5] = 100;
		elastic_criteria[6] = 70;
		elastic_criteria[7] = 70;
		
		fall_back_criteria[0] = 20;
		fall_back_criteria[1] = 70; 
		fall_back_criteria[2] = 0;  
		fall_back_criteria[3] = 20;
		fall_back_criteria[4] = 70;
		fall_back_criteria[5] = 50;
		fall_back_criteria[6] = 70;
		fall_back_criteria[7] = 100; 
		
		duplicate_criteria[0] = 20;
		duplicate_criteria[1] = 70; 
		duplicate_criteria[2] = 0;  
		duplicate_criteria[3] = 40;
		duplicate_criteria[4] = 100;
		duplicate_criteria[5] = 50;
		duplicate_criteria[6] = 70;
		duplicate_criteria[7] = 70;
		
		parallel_criteria[0] = 0;
		parallel_criteria[1] = 10; 
		parallel_criteria[2] = 100;
		parallel_criteria[3] = 100;
		parallel_criteria[4] = 100;
		parallel_criteria[5] = 50; 
		parallel_criteria[6] = 100;
		parallel_criteria[7] = 70;
		
		cloud_edge_only_criteria[0] = 100;
		cloud_edge_only_criteria[1] = 70; 
		cloud_edge_only_criteria[2] = 0; 
		cloud_edge_only_criteria[3] = 0;
		cloud_edge_only_criteria[4] = 0;
		cloud_edge_only_criteria[5] = 50;
		cloud_edge_only_criteria[6] = 50;
		cloud_edge_only_criteria[7] = 0;
		
		minMaxNormalizationAppModelCriteria();
		
		//Possible Results from an AHP
		/*
			vehicle_only_criteria[0] = 0.04347826087;
			vehicle_only_criteria[1] = 0.00;
			vehicle_only_criteria[2] = 0.50;
			vehicle_only_criteria[3] = 0.2666666667;
			vehicle_only_criteria[4] = 0.2127659574;
			vehicle_only_criteria[5] = 0.00;
			vehicle_only_criteria[6] = 0.00;
			vehicle_only_criteria[7] = 0.1388888889;
			
			elastic_criteria[0] = 0.347826087;
			elastic_criteria[1] = 0.2903225806; 
			elastic_criteria[2] = 0.00; 
			elastic_criteria[3] = 0.20;
			elastic_criteria[4] = 0.2127659574;
			elastic_criteria[5] = 0.3333333333;
			elastic_criteria[6] = 0.1944444444;
			elastic_criteria[7] = 0.1944444444;

			fall_back_criteria[0] = 0.08695652174;
			fall_back_criteria[1] = 0.2258064516; 
			fall_back_criteria[2] = 0.00;  
			fall_back_criteria[3] = 0.06666666667;
			fall_back_criteria[4] = 0.1489361702;
			fall_back_criteria[5] = 0.1666666667;
			fall_back_criteria[6] = 0.1944444444;
			fall_back_criteria[7] = 0.2777777778; 
			
			duplicate_criteria[0] = 0.08695652174;
			duplicate_criteria[1] = 0.2258064516; 
			duplicate_criteria[2] = 0.00;  
			duplicate_criteria[3] = 0.1333333333;
			duplicate_criteria[4] = 0.2127659574;
			duplicate_criteria[5] = 0.1666666667;
			duplicate_criteria[6] = 0.1944444444;
			duplicate_criteria[7] = 0.1944444444;
		
			parallel_criteria[0] = 0.00;
			parallel_criteria[1] = 0.03225806452; 
			parallel_criteria[2] = 0.50;
			parallel_criteria[3] = 0.3333333333;
			parallel_criteria[4] = 0.2127659574;
			parallel_criteria[5] = 0.1666666667; 
			parallel_criteria[6] = 0.2777777778;
			parallel_criteria[7] = 0.1944444444;
			
			cloud_edge_only_criteria[0] = 0.4347826087;
			cloud_edge_only_criteria[1] = 0.2258064516; 
			cloud_edge_only_criteria[2] = 0.00; 
			cloud_edge_only_criteria[3] = 0.00;
			cloud_edge_only_criteria[4] = 0.00;
			cloud_edge_only_criteria[5] = 0.1666666667;
			cloud_edge_only_criteria[6] = 0.1388888889;
			cloud_edge_only_criteria[7] = 0.00;
		*/		

		//-----------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------
		//--------------Testinitaliazation of importance factors -------------------------------
		//-----------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------
		
		//Convention: 
		// 0.0 	--> not important
		// 1.0 	--> extremely important
		// SUM --> 1

		//Initialaize Importance_factors for each of the criteria with "default values"
		//for(int i=0; i<ApplicationDecision.getNrofcriteria(); i++) {	
		//	importance_factor[i] = 1.0;
		//}
		
		//Set initial custom importance factors for each of the criteria	
		//FOR THE INITIAL CALCULATION!!!
		importance_factor[0] = 0.125;
		importance_factor[1] = 0.125;
		importance_factor[2] = 0.125;
		importance_factor[3] = 0.125;
		importance_factor[4] = 0.125;
		importance_factor[5] = 0.125;
		importance_factor[6] = 0.125;
		importance_factor[7] = 0.125;
		
		//0. Availability/Robustness
		//1. Latency
		//2. Processing Performance
		//3. Energy consumption  of Vehicle
		//5. Necessary On-Board Hardware
		//5. Security 
		//6. Operation Expenses 
		//7. Capital Expenditures
		
	
	}

	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	
	public double[] getImportance_factor() {
		return importance_factor;
	}

	public double[] getVehicle_only_criteria() {
		return vehicle_only_criteria;
	}


	public double[] getElastic_criteria() {
		return elastic_criteria;
	}


	public double[] getFall_back_criteria() {
		return fall_back_criteria;
	}


	public double[] getDuplicate_criteria() {
		return duplicate_criteria;
	}


	public double[] getParallel_criteria() {
		return parallel_criteria;
	}


	public double[] getCloud_edge_only_criteria() {
		return cloud_edge_only_criteria;
	}

	public void setVehicle_only_criteria(double[] vehicle_only_criteria) {
		this.vehicle_only_criteria = vehicle_only_criteria;
	}

	public void setElastic_criteria(double[] elastic_criteria) {
		this.elastic_criteria = elastic_criteria;
	}

	public void setFall_back_criteria(double[] fall_back_criteria) {
		this.fall_back_criteria = fall_back_criteria;
	}

	public void setDuplicate_criteria(double[] duplicate_criteria) {
		this.duplicate_criteria = duplicate_criteria;
	}

	public void setParallel_criteria(double[] parallel_criteria) {
		this.parallel_criteria = parallel_criteria;
	}

	public void setCloud_edge_only_criteria(double[] cloud_edge_only_criteria) {
		this.cloud_edge_only_criteria = cloud_edge_only_criteria;
	}
	
	public void setImportance_factor(double[] importance_factor) {
		this.importance_factor = importance_factor;
		
		/*
		System.out.println("Printing importance_factor:");
		for(int i=0; i<8; i++) {
			System.out.println(this.importance_factor[i]);
		}
		*/
	}
	
	//Normalizes the Values for the application model threshholds
	public void minMaxNormalizationAppModelCriteria() {
		
		double min_value = 0.0;
		double max_value = 0.0;
		
		for(int i=0; i<ApplicationDecision.getNrofcriteria(); i++) {
			min_value = 0.0;
			max_value = 0.0;
			
			if(vehicle_only_criteria[i]<min_value) min_value = vehicle_only_criteria[i];
			if(vehicle_only_criteria[i]>max_value) max_value = vehicle_only_criteria[i];
			if(elastic_criteria[i]<min_value) min_value = elastic_criteria[i];
			if(elastic_criteria[i]>max_value) max_value = elastic_criteria[i];
			if(fall_back_criteria[i]<min_value) min_value = fall_back_criteria[i];
			if(fall_back_criteria[i]>max_value) max_value = fall_back_criteria[i];
			if(duplicate_criteria[i]<min_value) min_value = duplicate_criteria[i];
			if(duplicate_criteria[i]>max_value) max_value = duplicate_criteria[i];
			if(parallel_criteria[i]<min_value) min_value = parallel_criteria[i];
			if(parallel_criteria[i]>max_value) max_value = parallel_criteria[i];
			if(cloud_edge_only_criteria[i]<min_value) min_value = cloud_edge_only_criteria[i];
			if(cloud_edge_only_criteria[i]>max_value) max_value = cloud_edge_only_criteria[i];
			
			vehicle_only_criteria[i] = (vehicle_only_criteria[i]-min_value)/(max_value-min_value);
			elastic_criteria[i] = (elastic_criteria[i]-min_value)/(max_value-min_value);
	    	fall_back_criteria[i] = (fall_back_criteria[i]-min_value)/(max_value-min_value);
	    	duplicate_criteria[i] = (duplicate_criteria[i]-min_value)/(max_value-min_value);
	    	parallel_criteria[i] = (parallel_criteria[i]-min_value)/(max_value-min_value);
	    	cloud_edge_only_criteria[i] = (cloud_edge_only_criteria[i]-min_value)/(max_value-min_value);
		}
		
		//test-print of the values!
		/*
		for(int i=0; i<ApplicationDecision.getNrofcriteria(); i++) {
			System.out.print("Criteria "+i+": ");
			System.out.print("\t"+vehicle_only_criteria[i]);
			System.out.print("\t"+elastic_criteria[i]);
			System.out.print("\t"+fall_back_criteria[i]);
			System.out.print("\t"+duplicate_criteria[i]);
			System.out.print("\t"+parallel_criteria[i]);
			System.out.println("\t"+cloud_edge_only_criteria[i]);
		}
		*/
		
		
	}
	
}
