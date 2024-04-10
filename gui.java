package applicationModels;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Locale;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gui implements FocusListener {

	//DATA-Objects
	private static SWCData swcData;
	private static ApplicationModelData applicationModelData;
	//GUI-Objects
	private JFrame frmApplicationSuitabilityTester;
	private JLabel lblMemoryRequirements, lblSlider_memory, lblSlider_data_dependencies, lblSlider_availability, lblSlider_latency, lblSlider_performance, lblSlider_security; 
	private JLabel lblVehicle_Only_Value, lblElastic_Value, lblDuplicate_Value, lblParallel_Value, lblFallback_Value, lblCloudEdgeOnly_Value;
	private JLabel lblSlider_security_importance, lblSlider_availability_importance, lblSlider_latency_importance, lblSlider_resource_restriction_importance,
					lblSlider_vehicle_energy_consumption_importance, lblSlider_capital_expenses_importance, lblSlider_operation_expenses_importance,
					lblSlider_resource_demand_importance;
	private JSlider slider_memory, slider_latency, slider_performance, slider_security, slider_data_dependencies, slider_availability;
	private JSlider slider_availability_importance, slider_security_importance, slider_capital_expenses_importance, slider_operation_expenses_importance, 
					slider_resource_restriction_importance, slider_vehicle_resource_demand_importance, slider_vehicle_energy_consumption_importance, 
					slider_latency_importance;
	private JCheckBox chckbxWeightedHarmonicMean;
	

	private static double results_mean[] = new double[ApplicationDecision.getNrofapplicationmodels()];
	
	private ChangeListener sliderChangeListener = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			sliderStateChangedFn(e);
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Invoke Objects from the other classes
		swcData = new SWCData();
		applicationModelData = new ApplicationModelData();
		new ApplicationDecision(applicationModelData, swcData);
		
		///*
		//Test-Runs and outputting the results to the console!!!
		//SWC-Criteria needs to be public!!!
		double results[] = new double[ApplicationDecision.getNrofapplicationmodels()];
		int nrOfSwcTests = 11; 
		
		//print out the initial importance values:
		System.out.println("Importance Values");
		for(int i=0; i<ApplicationDecision.getNrofcriteria(); i++) {
			System.out.println("Importance Factor "+i+": "+applicationModelData.getImportance_factor()[i]);
		}
		
		//print the results for the initial test-calculations
		swcData.setCriteria_01_navigation_services(); 
		results = ApplicationDecision.calculation(true);
		System.out.println("\n Suitability of 'navigation services': ");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results[i])+"%");
		}
		sum_results(results);
		
		swcData.setCriteria_02_parking_lot_search(); 
		results = ApplicationDecision.calculation(true);
		System.out.println("\n Suitability of 'parking lot search': ");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results[i])+"%");
		}
		sum_results(results);
		
		swcData.setCriteria_03_update_of_maps(); 
		results = ApplicationDecision.calculation(true);
		System.out.println("\n Suitability of 'update of maps': ");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results[i])+"%");
		}
		sum_results(results);
		
		swcData.setCriteria_04_video_streaming(); 
		results = ApplicationDecision.calculation(true);
		System.out.println("\n Suitability of 'video streaming': ");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results[i])+"%");
		}
		sum_results(results);
		
		swcData.setCriteria_05_predictive_maintenance(); 
		results = ApplicationDecision.calculation(true);
		System.out.println("\n Suitability of 'pred maintenance': ");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results[i])+"%");
		}
		sum_results(results);
		
		swcData.setCriteria_06_ImageRec(); 
		results = ApplicationDecision.calculation(true);
		System.out.println("\n Suitability of 'Image rec for ADAS': ");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results[i])+"%");
		}
		sum_results(results);
		
		swcData.setCriteria_07_range_estimation(); 
		results = ApplicationDecision.calculation(true);
		System.out.println("\n Suitability of 'range estimation': ");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results[i])+"%");
		}
		sum_results(results);
		
		swcData.setCriteria_08_hvac_control(); 
		results = ApplicationDecision.calculation(true);
		System.out.println("\n Suitability of 'HVAC control': ");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results[i])+"%");
		}
		sum_results(results);
		
		swcData.setCriteria_09_gesture_control(); 
		results = ApplicationDecision.calculation(true);
		System.out.println("\n Suitability of 'gestures control': ");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results[i])+"%");
		}
		sum_results(results);
		
		swcData.setCriteria_10_driver_profile(); 
		results = ApplicationDecision.calculation(true);
		System.out.println("\n Suitability of 'driver profile': ");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results[i])+"%");
		}
		sum_results(results);
		
		swcData.setCriteria_11_speed_limit_warning(); 
		results = ApplicationDecision.calculation(true);
		System.out.println("\n Suitability of 'speed limit warner': ");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results[i])+"%");
		}
		sum_results(results);
		
		//calculate mean of the results
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			results_mean[i] = results_mean[i]/nrOfSwcTests;
		}
		
		//outputting the mean values over all SWCs for each of the application models
		System.out.println("\n \n \n");
		System.out.println("Printing the mean of all suitabilities of all SWCs:");
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			System.out.println("--model "+getModelString(i)+"\t: "+String.format(Locale.GERMAN, "%,.2f", results_mean[i])+"%");
		}
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frmApplicationSuitabilityTester.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void sum_results(double[] results) {
		
		for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
			results_mean[i] += results[i];
		}
		
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmApplicationSuitabilityTester = new JFrame();
		frmApplicationSuitabilityTester.setTitle("OptiCAM - Optimal Cloud/Edge Application Model  ");
		frmApplicationSuitabilityTester.setBounds(100, 100, 960, 677);
		frmApplicationSuitabilityTester.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmApplicationSuitabilityTester.getContentPane().setLayout(null);
		
		lblMemoryRequirements = new JLabel("Memory Requirements");
		lblMemoryRequirements.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMemoryRequirements.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMemoryRequirements.setBounds(20, 160, 160, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblMemoryRequirements);
		
		JLabel lblDataDependencies = new JLabel("Data Dependencies");
		lblDataDependencies.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataDependencies.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDataDependencies.setBounds(20, 230, 160, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblDataDependencies);
		
		JLabel lblAvailabilityRequirements = new JLabel("Availability Requirements");
		lblAvailabilityRequirements.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAvailabilityRequirements.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAvailabilityRequirements.setBounds(20, 90, 160, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblAvailabilityRequirements);
		
		JLabel lblLatencyRequirements = new JLabel("Latency Requirements");
		lblLatencyRequirements.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLatencyRequirements.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLatencyRequirements.setBounds(20, 125, 160, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblLatencyRequirements);
		
		JLabel lblPerformanceRequirements = new JLabel("Performance Requirements");
		lblPerformanceRequirements.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPerformanceRequirements.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPerformanceRequirements.setBounds(10, 195, 170, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblPerformanceRequirements);
		
		lblSlider_memory = new JLabel("0.1");
		lblSlider_memory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_memory.setBounds(400, 160, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_memory);
		
		lblSlider_data_dependencies = new JLabel("0.1");
		lblSlider_data_dependencies.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_data_dependencies.setBounds(400, 230, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_data_dependencies);
		
		lblSlider_availability = new JLabel("0.1");
		lblSlider_availability.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_availability.setBounds(400, 90, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_availability);
				
		lblSlider_latency = new JLabel("0.1");
		lblSlider_latency.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_latency.setBounds(400, 125, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_latency);
		
		lblSlider_performance = new JLabel("0.1");
		lblSlider_performance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_performance.setBounds(400, 195, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_performance);
		
		lblSlider_security = new JLabel("0.1");
		lblSlider_security.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_security.setBounds(400, 265, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_security);
		
		
		
		
		slider_memory = new JSlider();
		slider_memory.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_memory.setText(Double.toString((Double.valueOf(slider_memory.getValue())/100)));
			}
		});
		slider_memory.setValue((int) (100 * swcData.getSwc_criteria()[2]));
		slider_memory.setBounds(190, 160, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_memory);
		
		slider_data_dependencies = new JSlider();
		slider_data_dependencies.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_data_dependencies.setText(Double.toString((Double.valueOf(slider_data_dependencies.getValue())/100)));
			}
		});
		slider_data_dependencies.setValue((int) (100 * swcData.getSwc_criteria()[4]));
		slider_data_dependencies.setBounds(190, 230, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_data_dependencies);
		
		slider_availability = new JSlider();
		slider_availability.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_availability.setText(Double.toString((Double.valueOf(slider_availability.getValue())/100)));

			}
		});
		slider_availability.setValue((int) (100 * swcData.getSwc_criteria()[0]));
		slider_availability.setBounds(190, 90, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_availability);
		
		slider_latency = new JSlider();
		slider_latency.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_latency.setText(Double.toString((Double.valueOf(slider_latency.getValue())/100)));
			}
		});
		slider_latency.setValue((int) (100 * swcData.getSwc_criteria()[1]));
		slider_latency.setBounds(190, 125, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_latency);
		
		slider_performance = new JSlider();
		slider_performance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_performance.setText(Double.toString((Double.valueOf(slider_performance.getValue())/100)));
			}
		});
		slider_performance.setValue((int) (100 * swcData.getSwc_criteria()[3]));
		slider_performance.setBounds(190, 195, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_performance);
		
		slider_security = new JSlider();
		slider_security.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_security.setText(Double.toString((Double.valueOf(slider_security.getValue())/100)));
			}
		});
		slider_security.setValue((int) (100 * swcData.getSwc_criteria()[5]));
		slider_security.setBounds(190, 265, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_security);

		
		
		JLabel lblSecurityRequirements = new JLabel("Security Requirements");
		lblSecurityRequirements.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSecurityRequirements.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSecurityRequirements.setBounds(20, 265, 160, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSecurityRequirements);
		
		JLabel lblSwcRequirements = new JLabel("SWC Requirements");
		lblSwcRequirements.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSwcRequirements.setBounds(100, 35, 157, 34);
		frmApplicationSuitabilityTester.getContentPane().add(lblSwcRequirements);
		
		JLabel lblImportanceFactors = new JLabel("Importance Factors");
		lblImportanceFactors.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblImportanceFactors.setBounds(567, 40, 149, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblImportanceFactors);

		JLabel lblSuitabilityForApplication = new JLabel("Suitability for Application Model");
		lblSuitabilityForApplication.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSuitabilityForApplication.setBounds(100, 370, 275, 34);
		frmApplicationSuitabilityTester.getContentPane().add(lblSuitabilityForApplication);
		
		JLabel lblVehicleOnly = new JLabel("Vehicle Only");
		lblVehicleOnly.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVehicleOnly.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVehicleOnly.setBounds(36, 410, 97, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblVehicleOnly);
		
		JLabel lblElastic = new JLabel("Elastic");
		lblElastic.setHorizontalAlignment(SwingConstants.RIGHT);
		lblElastic.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblElastic.setBounds(36, 445, 97, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblElastic);
		
		JLabel lblFallback = new JLabel("Fallback");
		lblFallback.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFallback.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFallback.setBounds(36, 480, 97, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblFallback);
		
		JLabel lblDuplicate = new JLabel("Duplicate");
		lblDuplicate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuplicate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDuplicate.setBounds(36, 515, 97, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblDuplicate);
		
		JLabel lblParallel = new JLabel("Parallel");
		lblParallel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblParallel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblParallel.setBounds(36, 550, 97, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblParallel);
		
		JLabel lblCloudEdgeOnly = new JLabel("Cloud/Edge Only");
		lblCloudEdgeOnly.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCloudEdgeOnly.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCloudEdgeOnly.setBounds(10, 585, 123, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblCloudEdgeOnly);
		
		lblVehicle_Only_Value = new JLabel("100%");
		lblVehicle_Only_Value.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVehicle_Only_Value.setBounds(143, 410, 97, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblVehicle_Only_Value);
		
		lblElastic_Value = new JLabel("100%");
		lblElastic_Value.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblElastic_Value.setBounds(143, 445, 97, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblElastic_Value);
		
		lblFallback_Value = new JLabel("100%");
		lblFallback_Value.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFallback_Value.setBounds(143, 480, 97, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblFallback_Value);
		
		lblDuplicate_Value = new JLabel("100%");
		lblDuplicate_Value.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDuplicate_Value.setBounds(143, 515, 97, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblDuplicate_Value);
		
		lblParallel_Value = new JLabel("100%");
		lblParallel_Value.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblParallel_Value.setBounds(143, 550, 97, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblParallel_Value);
		
		lblCloudEdgeOnly_Value = new JLabel("100%");
		lblCloudEdgeOnly_Value.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCloudEdgeOnly_Value.setBounds(143, 585, 97, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblCloudEdgeOnly_Value);
		
		chckbxWeightedHarmonicMean = new JCheckBox("Weighted Harmonic Mean");
		chckbxWeightedHarmonicMean.setSelected(true);
		chckbxWeightedHarmonicMean.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				setSuitabilityToGui();
			}
		});
		chckbxWeightedHarmonicMean.setBounds(247, 410, 185, 23);
		frmApplicationSuitabilityTester.getContentPane().add(chckbxWeightedHarmonicMean);
		
		JLabel lblAvailabilityImportance = new JLabel("Availability Importance");
		lblAvailabilityImportance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAvailabilityImportance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAvailabilityImportance.setBounds(480, 90, 210, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblAvailabilityImportance);
		
		JLabel lblLatencyImportance = new JLabel("Latency Importance");
		lblLatencyImportance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLatencyImportance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLatencyImportance.setBounds(480, 125, 210, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblLatencyImportance);
		
		JLabel lblResourceRestrictionImportance = new JLabel("Resource Restriction Imp.");
		lblResourceRestrictionImportance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResourceRestrictionImportance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblResourceRestrictionImportance.setBounds(480, 160, 210, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblResourceRestrictionImportance);
		
		JLabel lblVehicleEnergyConsumptionImportance = new JLabel("Vehicle Energy Consumption Imp.");
		lblVehicleEnergyConsumptionImportance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVehicleEnergyConsumptionImportance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVehicleEnergyConsumptionImportance.setBounds(480, 195, 210, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblVehicleEnergyConsumptionImportance);
		
		JLabel lblSecurityImportance_1 = new JLabel("Security Importance");
		lblSecurityImportance_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSecurityImportance_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSecurityImportance_1.setBounds(480, 265, 210, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSecurityImportance_1);
		
		JLabel lblVehicleResourceDemand = new JLabel("Vehicle Resource Demand Imp.");
		lblVehicleResourceDemand.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVehicleResourceDemand.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVehicleResourceDemand.setBounds(480, 230, 210, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblVehicleResourceDemand);
		
		JLabel lblOperationExpensesImportance = new JLabel("Operation Expenses Importance");
		lblOperationExpensesImportance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOperationExpensesImportance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOperationExpensesImportance.setBounds(480, 300, 210, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblOperationExpensesImportance);
		
		JLabel lblCapitalExpensesImportance = new JLabel("Capital Expenses Importance");
		lblCapitalExpensesImportance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCapitalExpensesImportance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCapitalExpensesImportance.setBounds(480, 335, 210, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblCapitalExpensesImportance);
		
		lblSlider_availability_importance = new JLabel("0.125");
		lblSlider_availability_importance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_availability_importance.setBounds(900, 90, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_availability_importance);
		
		lblSlider_latency_importance = new JLabel("0.125");
		lblSlider_latency_importance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_latency_importance.setBounds(900, 125, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_latency_importance);
		
		lblSlider_resource_restriction_importance = new JLabel("0.125");
		lblSlider_resource_restriction_importance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_resource_restriction_importance.setBounds(900, 160, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_resource_restriction_importance);
		
		lblSlider_resource_demand_importance = new JLabel("0.125");
		lblSlider_resource_demand_importance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_resource_demand_importance.setBounds(900, 230, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_resource_demand_importance);
		
		lblSlider_security_importance = new JLabel("0.125");
		lblSlider_security_importance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_security_importance.setBounds(900, 265, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_security_importance);
		
		lblSlider_operation_expenses_importance = new JLabel("0.125");
		lblSlider_operation_expenses_importance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_operation_expenses_importance.setBounds(900, 300, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_operation_expenses_importance);
		
		lblSlider_capital_expenses_importance = new JLabel("0.125");
		lblSlider_capital_expenses_importance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_capital_expenses_importance.setBounds(900, 335, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_capital_expenses_importance);
		
		lblSlider_vehicle_energy_consumption_importance = new JLabel("0.125");
		lblSlider_vehicle_energy_consumption_importance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSlider_vehicle_energy_consumption_importance.setBounds(900, 195, 33, 26);
		frmApplicationSuitabilityTester.getContentPane().add(lblSlider_vehicle_energy_consumption_importance);
		
		slider_availability_importance = new JSlider();
		slider_availability_importance.setPaintLabels(true);
		slider_availability_importance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_availability_importance.setText(Double.toString((Double.valueOf(slider_availability_importance.getValue())/1000)));
			}
		});
		
		slider_availability_importance.setMaximum(1000);
		slider_availability_importance.setValue(125);
		slider_availability_importance.setBounds(690, 90, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_availability_importance);
		
		slider_latency_importance = new JSlider();
		slider_latency_importance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_latency_importance.setText(Double.toString((Double.valueOf(slider_latency_importance.getValue())/1000)));
			}
		});
		
		slider_latency_importance.setMaximum(1000);
		slider_latency_importance.setValue(125);
		slider_latency_importance.setBounds(690, 125, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_latency_importance);
		
		slider_resource_restriction_importance = new JSlider();
		slider_resource_restriction_importance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_resource_restriction_importance.setText(Double.toString((Double.valueOf(slider_resource_restriction_importance.getValue())/1000)));
			}
		});
		
		slider_resource_restriction_importance.setMaximum(1000);
		slider_resource_restriction_importance.setValue(125);
		slider_resource_restriction_importance.setBounds(690, 160, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_resource_restriction_importance);
		
		slider_vehicle_energy_consumption_importance = new JSlider();
		slider_vehicle_energy_consumption_importance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_vehicle_energy_consumption_importance.setText(Double.toString((Double.valueOf(slider_vehicle_energy_consumption_importance.getValue())/1000)));
			}
		});
		
		slider_vehicle_energy_consumption_importance.setMaximum(1000);
		slider_vehicle_energy_consumption_importance.setValue(125);
		slider_vehicle_energy_consumption_importance.setBounds(690, 195, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_vehicle_energy_consumption_importance);
		
		slider_vehicle_resource_demand_importance = new JSlider();
		slider_vehicle_resource_demand_importance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_resource_demand_importance.setText(Double.toString((Double.valueOf(slider_vehicle_resource_demand_importance.getValue())/1000)));
			}
		});
		slider_vehicle_resource_demand_importance.setMaximum(1000);
		slider_vehicle_resource_demand_importance.setValue(125);
		slider_vehicle_resource_demand_importance.setBounds(690, 230, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_vehicle_resource_demand_importance);
		
		slider_security_importance = new JSlider();
		slider_security_importance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_security_importance.setText(Double.toString((Double.valueOf(slider_security_importance.getValue())/1000)));
			}
		});
		slider_security_importance.setMaximum(1000);
		slider_security_importance.setValue(125);
		slider_security_importance.setBounds(690, 265, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_security_importance);

		slider_capital_expenses_importance = new JSlider();
		slider_capital_expenses_importance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_capital_expenses_importance.setText(Double.toString((Double.valueOf(slider_capital_expenses_importance.getValue())/1000)));
			}
		});
		
		slider_capital_expenses_importance.setMaximum(1000);
		slider_capital_expenses_importance.setValue(125);
		slider_capital_expenses_importance.setBounds(690, 335, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_capital_expenses_importance);
		
		slider_operation_expenses_importance = new JSlider();
		slider_operation_expenses_importance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblSlider_operation_expenses_importance.setText(Double.toString((Double.valueOf(slider_operation_expenses_importance.getValue())/1000)));
			}
		});
		
		slider_operation_expenses_importance.setMaximum(1000);
		slider_operation_expenses_importance.setValue(125);
		slider_operation_expenses_importance.setBounds(690, 300, 200, 26);
		frmApplicationSuitabilityTester.getContentPane().add(slider_operation_expenses_importance);
		
		JButton btnResetFactors = new JButton("Reset Factors");
		btnResetFactors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAllChangeListeners();
				slider_availability_importance.setValue(125);
				slider_latency_importance.setValue(125);
				slider_resource_restriction_importance.setValue(125);
				slider_vehicle_energy_consumption_importance.setValue(125);
				slider_vehicle_resource_demand_importance.setValue(125);
				slider_security_importance.setValue(125);
				slider_operation_expenses_importance.setValue(125);
				slider_capital_expenses_importance.setValue(125);
				//set the importance factors to the data classes right away!;
				setImportanceFactorsGuiValuesToDataClasses();
				setSuitabilityToGui();
				setAllChangeListeners();
			}
		});
		btnResetFactors.setBounds(689, 370, 130, 23);
		frmApplicationSuitabilityTester.getContentPane().add(btnResetFactors);
		
		//Calculate new Values
		setSuitabilityToGui();
		
		//Set all of the ChangeListeners to be active only in the end!
		setAllChangeListeners();
	}
	
	//Function that gets called by the sliderStateChanged Listener Function
	public void sliderStateChangedFn(ChangeEvent e) { 
		//try {
		JSlider source = (JSlider)e.getSource();
		removeAllChangeListeners();
		if (!source.getValueIsAdjusting()) {
	    	//Recalculate the new values for the importances
	    	setImportanceValuesToGUI();	
	    	
	    	//Recalculate and show new values for the application models!
	    	setImportanceFactorsGuiValuesToDataClasses();
	    }
		
    	setSWCGuiValuesToDataClasses();
		setSuitabilityToGui();
		setAllChangeListeners();
	}

	//temporarily  all change listeners
	private void removeAllChangeListeners() {
		slider_capital_expenses_importance.removeChangeListener(sliderChangeListener);
		slider_memory.removeChangeListener(sliderChangeListener);
		slider_latency.removeChangeListener(sliderChangeListener);
		slider_performance.removeChangeListener(sliderChangeListener);
		slider_security.removeChangeListener(sliderChangeListener);
		slider_data_dependencies.removeChangeListener(sliderChangeListener);
		slider_availability.removeChangeListener(sliderChangeListener);
		slider_latency_importance.removeChangeListener(sliderChangeListener);
		slider_availability_importance.removeChangeListener(sliderChangeListener);
		slider_security_importance.removeChangeListener(sliderChangeListener);
		slider_capital_expenses_importance.removeChangeListener(sliderChangeListener);
		slider_operation_expenses_importance.removeChangeListener(sliderChangeListener);
		slider_resource_restriction_importance.removeChangeListener(sliderChangeListener);
		slider_vehicle_resource_demand_importance.removeChangeListener(sliderChangeListener);
		slider_vehicle_energy_consumption_importance.removeChangeListener(sliderChangeListener);
	}

	//adds all change listeners again
	private void setAllChangeListeners() {
		slider_capital_expenses_importance.addChangeListener(sliderChangeListener);
		slider_memory.addChangeListener(sliderChangeListener);
		slider_latency.addChangeListener(sliderChangeListener);
		slider_performance.addChangeListener(sliderChangeListener);
		slider_security.addChangeListener(sliderChangeListener);
		slider_data_dependencies.addChangeListener(sliderChangeListener);
		slider_availability.addChangeListener(sliderChangeListener);
		slider_latency_importance.addChangeListener(sliderChangeListener);
		slider_availability_importance.addChangeListener(sliderChangeListener);
		slider_security_importance.addChangeListener(sliderChangeListener);
		slider_capital_expenses_importance.addChangeListener(sliderChangeListener);
		slider_operation_expenses_importance.addChangeListener(sliderChangeListener);
		slider_resource_restriction_importance.addChangeListener(sliderChangeListener);
		slider_vehicle_resource_demand_importance.addChangeListener(sliderChangeListener);
		slider_vehicle_energy_consumption_importance.addChangeListener(sliderChangeListener);	
	}

	@Override
	public void focusGained(FocusEvent e) {
		// Nothing happens here
	}

	@Override
	public void focusLost(FocusEvent e) {
		// Nothing happens here
	}

	private void setImportanceValuesToGUI() {
		//check to see if any of the values from the importances has changed or not		
		double rest = 0.0;
		int i=-1;
		if(applicationModelData.getImportance_factor()[0] != ((double) slider_availability_importance.getValue())/1000) {
			i=0;
			rest = 1 - ((double) slider_availability_importance.getValue())/1000;}
		else if(applicationModelData.getImportance_factor()[1] != ((double) slider_latency_importance.getValue())/1000) {
			i=1;
			rest = 1 - ((double) slider_latency_importance.getValue())/1000;}
		else if(applicationModelData.getImportance_factor()[2] != ((double) slider_resource_restriction_importance.getValue())/1000) {
			i=2;
			rest = 1 - ((double) slider_resource_restriction_importance.getValue())/1000;}
		else if(applicationModelData.getImportance_factor()[3] != ((double) slider_vehicle_energy_consumption_importance.getValue())/1000) {
			i=3;
			rest = 1 - ((double) slider_vehicle_energy_consumption_importance.getValue())/1000;}
		else if(applicationModelData.getImportance_factor()[4] != ((double) slider_vehicle_resource_demand_importance.getValue())/1000) {
			i=4;
			rest = 1 - ((double) slider_vehicle_resource_demand_importance.getValue())/1000;}
		else if(applicationModelData.getImportance_factor()[5] != ((double) slider_security_importance.getValue())/1000) {
			i=5;
			rest = 1 - ((double) slider_security_importance.getValue())/1000;}
		else if(applicationModelData.getImportance_factor()[6] != ((double) slider_operation_expenses_importance.getValue())/1000) {
			i=6;
			rest = 1 - ((double) slider_operation_expenses_importance.getValue())/1000;}
		else if(applicationModelData.getImportance_factor()[7] != ((double) slider_capital_expenses_importance.getValue())/1000) {
			i=7;
			rest = 1 - ((double) slider_capital_expenses_importance.getValue())/1000;}
		
		if(i!=-1) {
			//some change is there --> adjust the values!
			double former_rest = 1.0 - applicationModelData.getImportance_factor()[i];//get the rest, that is still available to be distributed to the others
						
			//Rest gets distributed according to scale!
			double scale = rest / former_rest; 
			
			
			for(int j=0; j<ApplicationDecision.getNrofcriteria(); j++) {
				if(i!=j) { //only for the values that were unchanged
					switch(j) {
					case 0:
						slider_availability_importance.setValue((int) (slider_availability_importance.getValue() * scale));
						rest = rest - ((double) slider_availability_importance.getValue())/1000;
						break;
					case 1:
						slider_latency_importance.setValue((int) (slider_latency_importance.getValue() * scale));
						rest = rest - ((double) slider_latency_importance.getValue())/1000;
						break;
					case 2:
						slider_resource_restriction_importance.setValue((int) (slider_resource_restriction_importance.getValue() * scale));
						rest = rest - ((double) slider_resource_restriction_importance.getValue())/1000;
						break;
					case 3:
						slider_vehicle_energy_consumption_importance.setValue((int) (slider_vehicle_energy_consumption_importance.getValue() * scale));
						rest = rest - ((double) slider_vehicle_energy_consumption_importance.getValue())/1000;
						break;
					case 4:
						slider_vehicle_resource_demand_importance.setValue((int) (slider_vehicle_resource_demand_importance.getValue() * scale));
						rest = rest - ((double) slider_vehicle_resource_demand_importance.getValue())/1000;
						break;
					case 5:
						slider_security_importance.setValue((int) (slider_security_importance.getValue() * scale));
						rest = rest - ((double) slider_security_importance.getValue())/1000;
						break;
					case 6:
						slider_operation_expenses_importance.setValue((int) (slider_operation_expenses_importance.getValue() * scale));
						rest = rest - ((double) slider_operation_expenses_importance.getValue())/1000;
						break;
					case 7:
						slider_capital_expenses_importance.setValue((int) (slider_capital_expenses_importance.getValue() * scale));
						rest = rest - ((double) slider_capital_expenses_importance.getValue())/1000;
						break;
					}
				}
			}
			
			//Since the values of the slider can only be integers over time there is a drift of the values, hence we need to compensate for it!
			//Rest gets evenly distributed
			double newValue_double = rest/(ApplicationDecision.getNrofcriteria()-1);
			int newValue = (int) (newValue_double * 1000);
			
			for(int j=0; j<ApplicationDecision.getNrofcriteria(); j++) {
				if(i!=j) { //only for the values that were unchanged
					switch(j) {
					case 0:
						slider_availability_importance.setValue(slider_availability_importance.getValue()+newValue);
						break;
					case 1:
						slider_latency_importance.setValue(slider_latency_importance.getValue()+newValue);
						break;
					case 2:
						slider_resource_restriction_importance.setValue(slider_resource_restriction_importance.getValue()+newValue);
						break;
					case 3:
						slider_vehicle_energy_consumption_importance.setValue(slider_vehicle_energy_consumption_importance.getValue()+newValue);
						break;
					case 4:
						slider_vehicle_resource_demand_importance.setValue(slider_vehicle_resource_demand_importance.getValue()+newValue);
						break;
					case 5:
						slider_security_importance.setValue(slider_security_importance.getValue()+newValue);
						break;
					case 6:
						slider_operation_expenses_importance.setValue(slider_operation_expenses_importance.getValue()+newValue);
						break;
					case 7:
						slider_capital_expenses_importance.setValue(slider_capital_expenses_importance.getValue()+newValue);
						break;
					}
				}
			}
			
		}
	}
	
	public void setSWCGuiValuesToDataClasses() {
		double[] swc_criteria = new double[ApplicationDecision.getNrofswcrequirements()];
		
		swc_criteria[0] = (double) slider_availability.getValue()/100;
		swc_criteria[1] = (double) slider_latency.getValue()/100;
		swc_criteria[2] = (double) slider_memory.getValue()/100;
		swc_criteria[3] = (double) slider_performance.getValue()/100;
		swc_criteria[4] = (double) slider_data_dependencies.getValue()/100;		
		swc_criteria[5] = (double) slider_security.getValue()/100;
		
		/*
		System.out.println("Printing swc_criteria:");
		for(int i=0; i<6; i++) {
			System.out.println(swc_criteria[i]);
		}
		*/
		
		swcData.setSwc_criteria(swc_criteria);
	}
	

	public void setImportanceFactorsGuiValuesToDataClasses() {
		double[] importance_factor = new double[ApplicationDecision.getNrofcriteria()];
		
		importance_factor[0] = ((double) slider_availability_importance.getValue())/1000;
		importance_factor[1] = ((double) slider_latency_importance.getValue())/1000;
		importance_factor[2] = ((double) slider_resource_restriction_importance.getValue())/1000;
		importance_factor[3] = ((double) slider_vehicle_energy_consumption_importance.getValue())/1000;
		importance_factor[4] = ((double) slider_vehicle_resource_demand_importance.getValue())/1000;
		importance_factor[5] = ((double) slider_security_importance.getValue())/1000;
		importance_factor[6] = ((double) slider_operation_expenses_importance.getValue())/1000;
		importance_factor[7] = ((double) slider_capital_expenses_importance.getValue())/1000;
			
		applicationModelData.setImportance_factor(importance_factor);
		/*
		System.out.println("Printing importance_factor:");
		for(int i=0; i<8; i++) {
			System.out.println(importance_factor[i]);
		}
		*/
	}
	
	
	public void setSuitabilityToGui() {
        double[] suitability_percent = new double[ApplicationDecision.getNrofapplicationmodels()]; //Suitability in percent - compared to the max score possible!
		
		//calculate suitabilities
        suitability_percent = ApplicationDecision.calculation(chckbxWeightedHarmonicMean.isSelected());
        
        lblVehicle_Only_Value.setText(String.format(Locale.GERMAN, "%,.2f", suitability_percent[0]) + "%");
        lblElastic_Value.setText(String.format(Locale.GERMAN, "%,.2f", suitability_percent[1]) + "%");
        lblFallback_Value.setText(String.format(Locale.GERMAN, "%,.2f", suitability_percent[2]) + "%");
        lblDuplicate_Value.setText(String.format(Locale.GERMAN, "%,.2f", suitability_percent[3]) + "%");
        lblParallel_Value.setText(String.format(Locale.GERMAN, "%,.2f", suitability_percent[4]) + "%");
        lblCloudEdgeOnly_Value.setText(String.format(Locale.GERMAN, "%,.2f", suitability_percent[5]) + "%");
        
        //find highest suitability score of them all to highlight it!
        int nr_highest_suitability = 0;
        double highest_suitability = 0;
        for(int i=0; i<ApplicationDecision.getNrofapplicationmodels(); i++) {
        	if(highest_suitability<suitability_percent[i]) {
        		highest_suitability = suitability_percent[i];
        		nr_highest_suitability = i;
        	}
        }
        
        //set all texts to be non-bold
        lblVehicle_Only_Value.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
        lblElastic_Value.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
        lblFallback_Value.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
        lblDuplicate_Value.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
        lblParallel_Value.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
        lblCloudEdgeOnly_Value.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
        
        //set the highest suitability value to a bold font!
        if(nr_highest_suitability==0) 
            lblVehicle_Only_Value.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        else if(nr_highest_suitability==1) 
            lblElastic_Value.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        else if(nr_highest_suitability==2) 
            lblFallback_Value.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        else if(nr_highest_suitability==3) 
            lblDuplicate_Value.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        else if(nr_highest_suitability==4) 
            lblParallel_Value.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        else if(nr_highest_suitability==5)
            lblCloudEdgeOnly_Value.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
	}
	
	public static String getModelString(int i) {
        switch(i) {
        case 0:
        	return "'vehicle only'";
        case 1:
        	return "'elastic'";
        case 2:
        	return "'fall-back'";
        case 3:
        	return "'duplicate'";
        case 4:
        	return "'parallel'";
        default:
        	return "'cloud only'";
        }
        
	}
}
