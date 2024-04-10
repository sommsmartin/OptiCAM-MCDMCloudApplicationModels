package applicationModels;

public class SWCData {

	private double[] swc_criteria;
	
	public SWCData() {
	
    	//Initialize Criteria of the Function itself (a.k.a. Properties of the function!!!)
    	swc_criteria = new double[ApplicationDecision.getNrofcriteria()];
    	for(int i=0; i<ApplicationDecision.getNrofcriteria(); i++) {
        	swc_criteria[i] = 0.1; //LOWEST REQUIREMENTS
    	}
    	
    	//Testinitialiazation of swc-criteria!
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
    
    	
    	//CHOOSE AT MAX ONLY ONE!!!
    	
    	//setCriteria_small_easy_function();
    	//setCriteria_big_hard_function();
    	//setCriteria_single_test_values();  
    	
    	//setCriteria_01_real_time_navigation(); 
    	//setCriteria_02_parking_lot_search(); 
    	//setCriteria_03_update_of_maps(); 
    	//setCriteria_04_video_streaming(); 
    	//setCriteria_05_predictive_maintenance(); 
    	//setCriteria_06_teleop_driving(); 
    	//setCriteria_07_range_estimation(); 
    	//setCriteria_08_hvac_control(); 
    	//setCriteria_09_gesture_control(); 
    	//setCriteria_10_driver_profile(); 
    	//setCriteria_11_speed_limit_warning(); 
    	//setCriteria_12_EV_route_planning(); 
    	
    	
	
	}

	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	
	public double[] getSwc_criteria() {
		return swc_criteria;
	}
	

	public void setSwc_criteria(double[] swc_criteria) {
		this.swc_criteria = swc_criteria;
		
		/*
		System.out.println("Printing swc_criteria:");
		for(int i=0; i<8; i++) {
			System.out.println(this.swc_criteria[i]);
		}
		*/
	}
	
	/*
	private void setCriteria_small_easy_function() {
		//Testinitialiazation of swc-criteria!
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
		
		//Example small/easy function    	
    	swc_criteria[0]= 0.2 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.3 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.3 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.3 ; //--> little or high performance requirements?
    	swc_criteria[4]= 0.1 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.1 ; //--> little or high security requirements?
		
	}
	
	private void setCriteria_big_hard_function() {
		//Testinitialiazation of swc-criteria!
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
		
		//Example big/hard
    	swc_criteria[0]= 0.3 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.5 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.8 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.8 ; //--> little or high performance requirements?
    	swc_criteria[4]= 0.7 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.4 ; //--> little or high security requirements?
    	
	}
	
	private void setCriteria_single_test_values() {
		//Testinitialiazation of swc-criteria!
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
		
		//Single Test-Values

    	swc_criteria[0]= 1.0 ; //--> little or high availability requirements?
    	//swc_criteria[1]= 1.0 ; //--> little or high latency requirements?
    	//swc_criteria[2]= 1.0 ; //--> little or high memory requirements?
    	//swc_criteria[3]= 1.0 ; //--> little or high performance requirements?
    	//swc_criteria[4]= 1.0 ; //--> little or much data transferred to other functions
    	//swc_criteria[5]= 1.0; //--> little or high security requirements?
	}
	*/
	
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	void setCriteria_01_navigation_services() {
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
    	swc_criteria[0]= 0.3 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.4 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.8 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.8 ; //--> little or high performance requirements?
    	swc_criteria[4]= 0.2 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.1 ; //--> little or high security requirements?
    	
	}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	void setCriteria_02_parking_lot_search() {
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
    	swc_criteria[0]= 0.1 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.1 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.2 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.5; //--> little or high performance requirements?
    	swc_criteria[4]= 0.0 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.1 ; //--> little or high security requirements?
    	
	}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	void setCriteria_03_update_of_maps() {
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
    	swc_criteria[0]= 0.1 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.0 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.7 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.4 ; //--> little or high performance requirements?
    	swc_criteria[4]= 0.4 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.0 ; //--> little or high security requirements?
    	
	}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------

	void setCriteria_04_video_streaming() { //Youtube Streaming / Infotainment
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
    	swc_criteria[0]= 0.1 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.7 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.5 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.4 ; //--> little or high performance requirements?
    	swc_criteria[4]= 0.9 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.1 ; //--> little or high security requirements?
    	
	}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	void setCriteria_05_predictive_maintenance() {
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
    	swc_criteria[0]= 0.2 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.0 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.4 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.8 ; //--> little or high performance requirements?
    	swc_criteria[4]= 0.1 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.1 ; //--> little or high security requirements?
    	
	}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	void setCriteria_06_ImageRec() { //
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!    	
    	swc_criteria[0]= 0.9 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.9 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.5 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.5 ; //--> little or high performance requirements?
    	swc_criteria[4]= 0.5 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.8 ; //--> little or high security requirements?
    	
	}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	void setCriteria_07_range_estimation() {
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
    	swc_criteria[0]= 0.2 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.1 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.3 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.6 ; //--> little or high performance requirements?
    	swc_criteria[4]= 0.2 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.1 ; //--> little or high security requirements?
    	
	}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	void setCriteria_08_hvac_control() {
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
    	swc_criteria[0]= 0.2 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.1 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.1 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.1 ; //--> little or high performance requirements?
    	swc_criteria[4]= 0.1 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.0 ; //--> little or high security requirements?
    	
	}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	void setCriteria_09_gesture_control() {
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
    	swc_criteria[0]= 0.3 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.6 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.8 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.8 ; //--> little or high performance requirements?
    	swc_criteria[4]= 0.3 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.2 ; //--> little or high security requirements?
    	
	}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	void setCriteria_10_driver_profile() {
		//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
		swc_criteria[0]= 0.1 ; //--> little or high availability requirements?
		swc_criteria[1]= 0.0 ; //--> little or high latency requirements?
		swc_criteria[2]= 0.1 ; //--> little or high memory requirements?
		swc_criteria[3]= 0.2 ; //--> little or high performance requirements?
		swc_criteria[4]= 0.1 ; //--> little or much data transferred to other functions
		swc_criteria[5]= 0.6 ; //--> little or high security requirements?
		
	}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	void setCriteria_11_speed_limit_warning() {
    	//0.0 LOWEST REQUIREMENTS;      1.0 --> HIGHEST REQUIREMENTS!!
    	swc_criteria[0]= 0.1 ; //--> little or high availability requirements?
    	swc_criteria[1]= 0.3 ; //--> little or high latency requirements?
    	swc_criteria[2]= 0.4 ; //--> little or high memory requirements?
    	swc_criteria[3]= 0.5 ; //--> little or high performance requirements?
    	swc_criteria[4]= 0.1 ; //--> little or much data transferred to other functions
    	swc_criteria[5]= 0.0 ; //--> little or high security requirements?
    	
	}
	
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	
	
}
