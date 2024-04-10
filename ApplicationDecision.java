package applicationModels;

public class ApplicationDecision {
	
	private static ApplicationModelData appModelData;
	private static SWCData swcData;
	
	public static final int NrOfApplicationModels = 6; 
	//0. Functions in Vehicle Only
	//1. Elastic
	//2. Fall-Back
	//3. Duplicate
	//4. Parallel
	//5. Functions in Cloud/Edge Only

	public static int getNrofapplicationmodels() {
		return NrOfApplicationModels;
	}

	public static int getNrofcriteria() {
		return NrOfCriteria;
	}

	public static int getNrofswcrequirements() {
		return NrOfSWCRequirements;
	}


	public static final int NrOfCriteria = 8; 
	//Criteria for the decision on the Application Models
	//0. Availability/Robustness/Functional Safety
	//1. Latency
	//2. Performance of the available platform 
	//3. Security Requirements
	//4. Energy Consumption of Vehicle
	//5. Resource Demand on Vehicle Hardware for Execution
	//6. Operation Expenses
	//7. Capital Expenses
	
	
	public static final int NrOfSWCRequirements= 6;
	//Requirements/Properties of the Application
	//0. Availability/Robustness/Functional Safety Requirements
	//1. Latency Requirements
	//2. Memory Requirements 
	//3. Performance Requirements
	//4. Data Dependencies
	//5. Security Requirements
	
	
	public ApplicationDecision(ApplicationModelData amd, SWCData swcD) {
		appModelData = amd;
		swcData = swcD;
	}
	
	public static double[] calculation(boolean weightedHarmonicMean) {
		
    	//-----------------------------------------------------------------------------------
    	//-----------------------------------------------------------------------------------
    	//-----------------------------------------------------------------------------------
    	//-----------------------------------------------------------------------------------
        
        double[] suitability_score = new double[NrOfApplicationModels]; //Suitability in a Score for each of the application models
        double[] suitability_max_score = new double[NrOfApplicationModels]; //To evaluate the maximum suitability score for reference
        double[] suitability_percent = new double[NrOfApplicationModels]; //Suitability in percent - compared to the max score possible!
        
        double[][] part_result = new double[NrOfApplicationModels][NrOfCriteria]; //All the partial results of all criteria of all models
        double[][] part_result_max = new double[NrOfApplicationModels][NrOfCriteria]; //All the partial results of all criteria of all models
        
        //Combined Parameters
        double combinedPerformanceRequirements = 0.0;
        double combinedOperationExpenseParameters = 0.0;
        double combinedCapitalExpenseParameters = 0.0;
        
        //Go over every application model and evaluate each of its criteria!
        for(int applicationModel=0; applicationModel<NrOfApplicationModels; applicationModel++) {
        	//System.out.println("");
			//System.out.println("");
			//System.out.println("---Summing up suitability score of applicationModel "+ applicationModel);
			
        	for(int criteria=0; criteria<NrOfCriteria; criteria++) {
        		//Go through all of the criteria for evaluation 
        		
        		switch(criteria) {
        		case 0: //Availability/Robustness/Functional Safety
        			part_result[applicationModel][criteria] = criteria_0_evaluation(swcData.getSwc_criteria()[0], applicationModel);
            		part_result_max[applicationModel][criteria] = criteria_0_evaluation(0.0, applicationModel); //same evaluation with "0.0" for every value
        			break;
        			
        		case 1: //Latency
        			part_result[applicationModel][criteria] = criteria_1_evaluation(swcData.getSwc_criteria()[1], applicationModel);
            		part_result_max[applicationModel][criteria] = criteria_1_evaluation(0.0, applicationModel); //same evaluation with "0.0" for every value        			
        			break;
        			
        		case 2: //Performance of the available platform 
        			//Combine the memory and performance requirements of the SWCs
        			combinedPerformanceRequirements = (swcData.getSwc_criteria()[2] + swcData.getSwc_criteria()[3])/2;
        			part_result[applicationModel][criteria] = criteria_2_evaluation(combinedPerformanceRequirements, applicationModel);
            		part_result_max[applicationModel][criteria] = criteria_2_evaluation(0.0, applicationModel); //same evaluation with "0.0" for every value
        			break;
        			
        		case 3: //Energy Consumption of Vehicle
        			part_result[applicationModel][criteria] = criteria_3_evaluation(swcData.getSwc_criteria()[3], applicationModel);
            		part_result_max[applicationModel][criteria] = criteria_3_evaluation(0.0, applicationModel); //same evaluation with "0.0" for every value
        			break;
        			
        		case 4: //Resource Demand on Vehicle Hardware for Execution
        			//Combine the memory and performance requirements of the SWCs
        			combinedPerformanceRequirements = (swcData.getSwc_criteria()[2] + swcData.getSwc_criteria()[3])/2;
        			part_result[applicationModel][criteria] = criteria_4_evaluation(combinedPerformanceRequirements, applicationModel);
            		part_result_max[applicationModel][criteria] = criteria_4_evaluation(0.0, applicationModel); //same evaluation with "0.0" for every value
        			break;
        			
        		case 5: //Security Requirements
        			part_result[applicationModel][criteria] = criteria_5_evaluation(swcData.getSwc_criteria()[5], applicationModel);
            		part_result_max[applicationModel][criteria] = criteria_5_evaluation(0.0, applicationModel); //same evaluation with "0.0" for every value
        			break;
        			
        			
        		case 6: //Operation Expenses
        			combinedOperationExpenseParameters = (swcData.getSwc_criteria()[2] + swcData.getSwc_criteria()[3] + swcData.getSwc_criteria()[4])/3;
        			part_result[applicationModel][criteria] = criteria_6_evaluation(combinedOperationExpenseParameters, applicationModel);
            		part_result_max[applicationModel][criteria] = criteria_6_evaluation(0.0, applicationModel); //same evaluation with "0.0" for every value
        			break;
        			
        		case 7: //Capital Expenses
        			combinedCapitalExpenseParameters = (swcData.getSwc_criteria()[2] + swcData.getSwc_criteria()[4])/2;
        			part_result[applicationModel][criteria] = criteria_7_evaluation(combinedCapitalExpenseParameters, applicationModel);
            		part_result_max[applicationModel][criteria] = criteria_7_evaluation(0.0, applicationModel); //same evaluation with "0.0" for every value
        			break;
        		}
        		
        		//Do these two steps after every criteria evaluation
        		suitability_score[applicationModel] = suitability_score[applicationModel] + (part_result[applicationModel][criteria] * appModelData.getImportance_factor()[criteria]) ;
        		suitability_max_score[applicationModel] = suitability_max_score[applicationModel] + (part_result_max[applicationModel][criteria] * appModelData.getImportance_factor()[criteria]) ; 
        	
        		//System.out.println("SWC-Criteria: "+swcData.getSwc_criteria()[criteria] +"; Application Model: "+ applicationModel);
				//double result = ( evaluation_function.invoke(swc_criteria[criteria], applicationModel));
				//System.out.println("-Suitability score ("+criteria+"/"+NrOfCriteria+"): "+ suitability_score[applicationModel] + "/" + suitability_max_score[applicationModel]);
        	}

        	
			//System.out.println("---Summed up suitability score of applicationModel "+ applicationModel + ": " + suitability_score[applicationModel]);
			//System.out.println("---Max suitability score of applicationModel "+ applicationModel + ": " + suitability_max_score[applicationModel]);
        	//System.out.println("---Summed up suitability score of applicationModel "+ applicationModel + ": " + suitability_score[applicationModel] + "/" + suitability_max_score[applicationModel]);
        	//System.out.println("---Overall suitability of applicationModel "+ applicationModel + ": " + String.format(Locale.GERMAN, "%,.2f", (((double) suitability_score[applicationModel] / (double) suitability_max_score[applicationModel]))) + "%");
        	
        	
        	if(weightedHarmonicMean) {
	        	//Alternative suitability Percentage with Weighted Harmonic Sums!
	        	double constant = 1; //is not important actually since we are going for percentages anyways
	        	
	        	//Harmonic Mean with exclusion of 0's 
	        	double suitability_new = 0.0;
	        	double suitability_new_max = 0.0;
	        	double sum_importance_factors = 0.0;
	        	
	        	for(int i=0; i< NrOfCriteria; i++) {
	        		if(appModelData.getImportance_factor()[i]>0) { //Specifically skip values with importance "0.0"
	        			suitability_new += appModelData.getImportance_factor()[i]/(part_result[applicationModel][i]);
	        			suitability_new_max += appModelData.getImportance_factor()[i]/(part_result_max[applicationModel][i]);
	        		}
	        	}	        	
	        	
	        	//Sum up the importance factors for the inversion
	        	for(int i=0; i< NrOfCriteria; i++) {
        			sum_importance_factors += appModelData.getImportance_factor()[i];
	        	}
	        	
	        	//Invert values for the mean!
	        	suitability_new = (constant*sum_importance_factors)/suitability_new;
	        	suitability_new_max = (constant*sum_importance_factors)/suitability_new_max;

	        	//System.out.println(" ----- suitability_new after inversion: " + suitability_new);
	        	//System.out.println(" ----- suitability_new_max after inversion: " + suitability_new_max);
	        	//*/
	        	//System.out.println ("Suitability_new: " + suitability_new + "; Suitability_new_max: " + suitability_new);
	        	suitability_percent[applicationModel] = (((double) suitability_new / (double) suitability_new_max) * 100);
        	}
        	else { 
        		//Much simpler alternative with simple weighted mean
	        	suitability_percent[applicationModel] = (((double) suitability_score[applicationModel] / (double) suitability_max_score[applicationModel]) * 100);
        	}

    	}
        
        return suitability_percent;
        
    }
    
//-----------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------
	
  //0. Availability/Robustness
    public static double criteria_0_evaluation(double swc_criteria, int applicationModel) {
    	double result = 0.0;
    	
    	//Accesses the SWC-Requirement/Data: 
    	//0. Availability/Robustness/Functional Safety Requirements
    	
    	//***Application-Model Values***
    	//
    	//100 - No specific constraints on availability
    	//80 - Critical Moments in transfer possible
    	//20 - Very High (vehicle only)
    	//10 - Very High (when switching process is unproblematic)
    	//0 - Highest
    	
    	switch(applicationModel) {
    	case 0://0. Functions in Vehicle Only
        	if((1-swc_criteria) < (appModelData.getVehicle_only_criteria()[0])) 
        		result = ( (((1-swc_criteria) / (appModelData.getVehicle_only_criteria()[0]))));
        	else
        		result = 1.0;
    		break;
    	case 1://Elastic
        	if((1-swc_criteria) < (appModelData.getElastic_criteria()[0])) 
        		result = ( (((1-swc_criteria) / (appModelData.getElastic_criteria()[0]))));
        	else
        		result = 1.0;
    		break;
    	case 2://Fall-Back
        	if((1-swc_criteria) < (appModelData.getFall_back_criteria()[0]))
        		result = ( (((1-swc_criteria) / (appModelData.getFall_back_criteria()[0]))));
        	else
        		result = 1.0;
    		break;
    	case 3://Duplicate
    		if((1-swc_criteria) < (appModelData.getDuplicate_criteria()[0]))
        		result = ( (((1-swc_criteria) / (appModelData.getDuplicate_criteria()[0]))));
        	else
        		result = 1.0;
    		break;
    	case 4://Parallel
    		if((1-swc_criteria) < (appModelData.getParallel_criteria()[0]))
        		result = ( (((1-swc_criteria) / (appModelData.getParallel_criteria()[0]))));
        	else
        		result = 1.0;
    		break;
    	case 5://Functions in Cloud/Edge Only
    		if((1-swc_criteria) < (appModelData.getCloud_edge_only_criteria()[0]))
        		result = ( (((1-swc_criteria) / (appModelData.getCloud_edge_only_criteria()[0]))));
        	else
        		result = 1.0;
    		break;
    	default:
    		System.err.println("APPLICATION MODEL NOT FOUND FOR EVALUATION OF CRITERIA 0!!!");
    		break;
    	}
    	
    	return result;
    }
    
  //1. Latency
    public static double criteria_1_evaluation(double swc_criteria, int applicationModel) {
    	double result = 0.0;
    	
    	//Accesses the SWC-Requirement/Data: 
    	//1. Latency Requirements
    	
    	//***Application-Model Values***
    	//100 - No specific specs Latency
    	//90 - Dependant on Connection Latency and Switching Latencies as well
    	//70 - Dependant on Connection Latency
    	//10 - Parallel Execution
    	//0 - Lowest Possible (vehicle only)
    	
    	switch(applicationModel) {
    	case 0://0. Functions in Vehicle Only
        	if((1-swc_criteria) < (appModelData.getVehicle_only_criteria()[1])) 
        		result = ( (((1-swc_criteria) / (appModelData.getVehicle_only_criteria()[1]))));
        	else
        		result = 1.0;
    		break;
    	case 1://Elastic
        	if((1-swc_criteria) < (appModelData.getElastic_criteria()[1])) 
        		result = ( (((1-swc_criteria) / (appModelData.getElastic_criteria()[1]))));
        	else
        		result = 1.0;
    		break;
    	case 2://Fall-Back
        	if((1-swc_criteria) < (appModelData.getFall_back_criteria()[1]))
        		result = ( (((1-swc_criteria) / (appModelData.getFall_back_criteria()[1]))));
        	else
        		result = 1.0;
    		break;
    	case 3://Duplicate
    		if((1-swc_criteria) < (appModelData.getDuplicate_criteria()[1]))
        		result = ( (((1-swc_criteria) / (appModelData.getDuplicate_criteria()[1]))));
        	else
        		result = 1.0;
    		break;
    	case 4://Parallel
    		if((1-swc_criteria) < (appModelData.getParallel_criteria()[1]))
        		result = ( (((1-swc_criteria) / (appModelData.getParallel_criteria()[1]))));
        	else
        		result = 1.0;
    		break;
    	case 5://Functions in Cloud/Edge Only
    		if((1-swc_criteria) < (appModelData.getCloud_edge_only_criteria()[1]))
        		result = ( (((1-swc_criteria) / (appModelData.getCloud_edge_only_criteria()[1]))));
        	else
        		result = 1.0;
    		break;
    	default:
    		System.err.println("APPLICATION MODEL NOT FOUND FOR EVALUATION OF CRITERIA 1!!!");
    		break;
    	}

    	return result;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
  
   
  //2. Resource Restrictions
    public static double criteria_2_evaluation(double swc_criteria, int applicationModel) {
    	double result = 0.0;
    	
    	//Accesses the SWC-Requirement/Data: 
    	//2. Memory Requirements 
    	//3. Performance Requirements
    	
    	//***Application-Model Values***
    	//
    	//100 - Limited to Vehicle Hardware - No specific requirements
    	//0 - Almost Limitless Performance of Cloud/Edge
    	
    	switch(applicationModel) {
    	case 0://0. Functions in Vehicle Only
        	if((1-swc_criteria) < (appModelData.getVehicle_only_criteria()[2])) 
        		result = ( (((1-swc_criteria) / (appModelData.getVehicle_only_criteria()[2]))));
        	else
        		result = 1.0;
    		break;
    	case 1://Elastic
        	if((1-swc_criteria) < (appModelData.getElastic_criteria()[2])) 
        		result = ( (((1-swc_criteria) / (appModelData.getElastic_criteria()[2]))));
        	else
        		result = 1.0;
    		break;
    	case 2://Fall-Back
        	if((1-swc_criteria) < (appModelData.getFall_back_criteria()[2]))
        		result = ( (((1-swc_criteria) / (appModelData.getFall_back_criteria()[2]))));
        	else
        		result = 1.0;
    		break;
    	case 3://Duplicate
    		if((1-swc_criteria) < (appModelData.getDuplicate_criteria()[2]))
        		result = ( (((1-swc_criteria) / (appModelData.getDuplicate_criteria()[2]))));
        	else
        		result = 1.0;
    		break;
    	case 4://Parallel
    		if((1-swc_criteria) < (appModelData.getParallel_criteria()[2]))
        		result = ( (((1-swc_criteria) / (appModelData.getParallel_criteria()[2]))));
        	else
        		result = 1.0;
    		break;
    	case 5://Functions in Cloud/Edge Only
    		if((1-swc_criteria) < (appModelData.getCloud_edge_only_criteria()[2]))
        		result = ( (((1-swc_criteria) / (appModelData.getCloud_edge_only_criteria()[2]))));
        	else
        		result = 1.0;
    		break;
    	default:
    		System.err.println("APPLICATION MODEL NOT FOUND FOR EVALUATION OF CRITERIA 2!!!");
    		break;
    	}

    	return result;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    

  	


  	//3. Energy Consumption of Vehicle
    public static double criteria_3_evaluation(double swc_criteria, int applicationModel) {
    	double result = 0.0;
    	
    	//Accesses the SWC-Requirement/Data: 
    	//3. Performance Requirements
    	
    	//***Application-Model Values***
    	//
    	//100 - Full consumption on vehicle + Communication Overhead
    	//80 - Full consumption on vehicle
    	//60 - Elastic - Consumption low + transfering-energy-consumption
    	//40 - Low since consumption is mostly in vehicle
    	//20 - Fallback - very low on vehicle usually
    	//0 - No additional consumption on the vehicle
    	
    	switch(applicationModel) {
    	case 0://0. Functions in Vehicle Only
        	if((1-swc_criteria) < (appModelData.getVehicle_only_criteria()[3])) 
        		result = ( (((1-swc_criteria) / (appModelData.getVehicle_only_criteria()[3]))));
        	else
        		result = 1.0;
    		break;
    	case 1://Elastic
        	if((1-swc_criteria) < (appModelData.getElastic_criteria()[3])) 
        		result = ( (((1-swc_criteria) / (appModelData.getElastic_criteria()[3]))));
        	else
        		result = 1.0;
    		break;
    	case 2://Fall-Back
        	if((1-swc_criteria) < (appModelData.getFall_back_criteria()[3]))
        		result = ( (((1-swc_criteria) / (appModelData.getFall_back_criteria()[3]))));
        	else
        		result = 1.0;
    		break;
    	case 3://Duplicate
    		if((1-swc_criteria) < (appModelData.getDuplicate_criteria()[3]))
        		result = ( (((1-swc_criteria) / (appModelData.getDuplicate_criteria()[3]))));
        	else
        		result = 1.0;
    		break;
    	case 4://Parallel
    		if((1-swc_criteria) < (appModelData.getParallel_criteria()[3]))
        		result = ( (((1-swc_criteria) / (appModelData.getParallel_criteria()[3]))));
        	else
        		result = 1.0;
    		break;
    	case 5://Functions in Cloud/Edge Only
    		if((1-swc_criteria) < (appModelData.getCloud_edge_only_criteria()[3]))
        		result = ( (((1-swc_criteria) / (appModelData.getCloud_edge_only_criteria()[3]))));
        	else
        		result = 1.0;
    		break;
    	default:
    		System.err.println("APPLICATION MODEL NOT FOUND FOR EVALUATION OF CRITERIA 3!!!");
    		break;
    	}
    	
    	return result;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    

  	//4. Requirements on Vehicle Hardware for function execution
    public static double criteria_4_evaluation(double swc_criteria, int applicationModel) {
    	double result = 0.0;
    	
    	//Accesses the SWC-Requirement/Data: 
    	//2. Memory Requirements 
    	//3. Performance Requirements
    	
    	//***Application-Model Values***
    	//
    	//100 - Full requirements on vehicle hardware
    	//70 - Fallback - very low on vehicle depending on implementation 
    	//0 - No additional vehicle requirements - Cloud/Edge only
    	
    	switch(applicationModel) {
    	case 0://0. Functions in Vehicle Only
        	if((1-swc_criteria) < (appModelData.getVehicle_only_criteria()[4])) 
        		result = ( (((1-swc_criteria) / (appModelData.getVehicle_only_criteria()[4]))));
        	else
        		result = 1.0;
    		break;
    	case 1://Elastic
        	if((1-swc_criteria) < (appModelData.getElastic_criteria()[4])) 
        		result = ( (((1-swc_criteria) / (appModelData.getElastic_criteria()[4]))));
        	else
        		result = 1.0;
    		break;
    	case 2://Fall-Back
        	if((1-swc_criteria) < (appModelData.getFall_back_criteria()[4]))
        		result = ( (((1-swc_criteria) / (appModelData.getFall_back_criteria()[4]))));
        	else
        		result = 1.0;
    		break;
    	case 3://Duplicate
    		if((1-swc_criteria) < (appModelData.getDuplicate_criteria()[4]))
        		result = ( (((1-swc_criteria) / (appModelData.getDuplicate_criteria()[4]))));
        	else
        		result = 1.0;
    		break;
    	case 4://Parallel
    		if((1-swc_criteria) < (appModelData.getParallel_criteria()[4]))
        		result = ( (((1-swc_criteria) / (appModelData.getParallel_criteria()[4]))));
        	else
        		result = 1.0;
    		break;
    	case 5://Functions in Cloud/Edge Only
    		if((1-swc_criteria) < (appModelData.getCloud_edge_only_criteria()[4]))
        		result = ( (((1-swc_criteria) / (appModelData.getCloud_edge_only_criteria()[4]))));
        	else
        		result = 1.0;
    		break;
    	default:
    		System.err.println("APPLICATION MODEL NOT FOUND FOR EVALUATION OF CRITERIA 4!!!");
    		break;
    	}
    	
    	return result;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    
  	//5. Security
    public static double criteria_5_evaluation(double swc_criteria, int applicationModel) {
    	double result = 0.0;
    	
    	//Accesses the SWC-Requirement/Data: 
    	//5. Security Requirements
    	
    	//***Application-Model Values***
    	//
    	//100 - No specific specs on Security
    	//50 - Medium Security - Functions are fixed on the platforms
    	//0 - Very High Security - Vehicle Only
    	
    	switch(applicationModel) {
    	case 0://0. Functions in Vehicle Only
        	if((1-swc_criteria) < (appModelData.getVehicle_only_criteria()[5])) 
        		result = ( (((1-swc_criteria) / (appModelData.getVehicle_only_criteria()[5]))));
        	else
        		result = 1.0;
    		break;
    	case 1://Elastic
        	if((1-swc_criteria) < (appModelData.getElastic_criteria()[5])) 
        		result = ( (((1-swc_criteria) / (appModelData.getElastic_criteria()[5]))));
        	else
        		result = 1.0;
    		break;
    	case 2://Fall-Back
        	if((1-swc_criteria) < (appModelData.getFall_back_criteria()[5]))
        		result = ( (((1-swc_criteria) / (appModelData.getFall_back_criteria()[5]))));
        	else
        		result = 1.0;
    		break;
    	case 3://Duplicate
    		if((1-swc_criteria) < (appModelData.getDuplicate_criteria()[5]))
        		result = ( (((1-swc_criteria) / (appModelData.getDuplicate_criteria()[5]))));
        	else
        		result = 1.0;
    		break;
    	case 4://Parallel
    		if((1-swc_criteria) < (appModelData.getParallel_criteria()[5]))
        		result = ( (((1-swc_criteria) / (appModelData.getParallel_criteria()[5]))));
        	else
        		result = 1.0;
    		break;
    	case 5://Functions in Cloud/Edge Only
    		if((1-swc_criteria) < (appModelData.getCloud_edge_only_criteria()[5]))
        		result = ( (((1-swc_criteria) / (appModelData.getCloud_edge_only_criteria()[5]))));
        	else
        		result = 1.0;
    		break;
    	default:
    		System.err.println("APPLICATION MODEL NOT FOUND FOR EVALUATION OF CRITERIA 5!!!");
    		break;
    	}

    	return result;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    
    
 	//6. Operation Expenses
    public static double criteria_6_evaluation(double swc_criteria, int applicationModel) {
    	double result = 0.0;
    	
    	//Accesses the SWC-Requirement/Data: 
    	//2. Memory Requirements 
    	//3. Performance Requirements
    	//4. Data Dependencies
    	
    	//***Application-Model Values***
    	//
    	//100 - Highest cost
    	//70 - High cost
    	//50 - Medium Cost
    	//0 - Lowest cost
    	
    	switch(applicationModel) {
    	case 0://0. Functions in Vehicle Only
        	if((1-swc_criteria) < (appModelData.getVehicle_only_criteria()[6])) 
        		result = ( (((1-swc_criteria) / (appModelData.getVehicle_only_criteria()[6]))));
        	else
        		result = 1.0;
    		break;
    	case 1://Elastic
        	if((1-swc_criteria) < (appModelData.getElastic_criteria()[6])) 
        		result = ( (((1-swc_criteria) / (appModelData.getElastic_criteria()[6]))));
        	else
        		result = 1.0;
    		break;
    	case 2://Fall-Back
        	if((1-swc_criteria) < (appModelData.getFall_back_criteria()[6]))
        		result = ( (((1-swc_criteria) / (appModelData.getFall_back_criteria()[6]))));
        	else
        		result = 1.0;
    		break;
    	case 3://Duplicate
    		if((1-swc_criteria) < (appModelData.getDuplicate_criteria()[6]))
        		result = ( (((1-swc_criteria) / (appModelData.getDuplicate_criteria()[6]))));
        	else
        		result = 1.0;
    		break;
    	case 4://Parallel
    		if((1-swc_criteria) < (appModelData.getParallel_criteria()[6]))
        		result = ( (((1-swc_criteria) / (appModelData.getParallel_criteria()[6]))));
        	else
        		result = 1.0;
    		break;
    	case 5://Functions in Cloud/Edge Only
    		if((1-swc_criteria) < (appModelData.getCloud_edge_only_criteria()[6]))
        		result = ( (((1-swc_criteria) / (appModelData.getCloud_edge_only_criteria()[6]))));
        	else
        		result = 1.0;
    		break;
    	default:
    		System.err.println("APPLICATION MODEL NOT FOUND FOR EVALUATION OF CRITERIA 6!!!");
    		break;
    	}

    	return result;
    }
  //7. Capital Expenses
    public static double criteria_7_evaluation(double swc_criteria, int applicationModel) {
    	double result = 0.0;
    	
    	//Accesses the SWC-Requirement/Data: 
    	//2. Memory Requirements 
    	//4. Data Dependencies
    	
    	//***Application-Model Values***
    	//
    	//100 - Highest cost
    	//70 - High cost
    	//50 - medium cost
    	//0 - Lowest cost
    	
    	switch(applicationModel) {
    	case 0://0. Functions in Vehicle Only
        	if((1-swc_criteria) < (appModelData.getVehicle_only_criteria()[7])) 
        		result = ( (((1-swc_criteria) / (appModelData.getVehicle_only_criteria()[7]))));
        	else
        		result = 1.0;
    		break;
    	case 1://Elastic
        	if((1-swc_criteria) < (appModelData.getElastic_criteria()[7])) 
        		result = ( (((1-swc_criteria) / (appModelData.getElastic_criteria()[7]))));
        	else
        		result = 1.0;
    		break;
    	case 2://Fall-Back
        	if((1-swc_criteria) < (appModelData.getFall_back_criteria()[7]))
        		result = ( (((1-swc_criteria) / (appModelData.getFall_back_criteria()[7]))));
        	else
        		result = 1.0;
    		break;
    	case 3://Duplicate
    		if((1-swc_criteria) < (appModelData.getDuplicate_criteria()[7]))
        		result = ( (((1-swc_criteria) / (appModelData.getDuplicate_criteria()[7]))));
        	else
        		result = 1.0;
    		break;
    	case 4://Parallel
    		if((1-swc_criteria) < (appModelData.getParallel_criteria()[7]))
        		result = ( (((1-swc_criteria) / (appModelData.getParallel_criteria()[7]))));
        	else
        		result = 1.0;
    		break;
    	case 5://Functions in Cloud/Edge Only
    		if((1-swc_criteria) < (appModelData.getCloud_edge_only_criteria()[7]))
        		result = ( (((1-swc_criteria) / (appModelData.getCloud_edge_only_criteria()[7]))));
        	else
        		result = 1.0;
    		break;
    	default:
    		System.err.println("APPLICATION MODEL NOT FOUND FOR EVALUATION OF CRITERIA 7!!!");
    		break;
    	}

    	return result;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------
}