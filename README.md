# Vehicle function offloading: Finding the Optimal Cloud/Edge Application Model (OptiCAM)
JAVA code for the OPTICAM Paper

Networking the vehicle with cloud or edge systems enables new features for the customer. On the other hand, existing vehicle functions or software components (SWCs) can also be offloaded to this network ecosystem. For example, it is conceivable that due to cost-intensive vehicle hardware, individual functions will no longer remain in the vehicle but will be executed in the cloud or edge. This means that the functions and their SWCs no longer remain statically deployed and executed on an execution node like an electronic control unit (ECU) but can be redeployed during the vehicle's life cycle. To make this possible, application models that take into account the individual requirements of automotive functions are needed. The paper proposes five different application models in addition to the standard in-vehicle application. The various features of the application models are predestined for the multiple-criteria decision-making (MCDM) approach. We propose OptiCAM, a MCDM model that helps software developers or electrical/electronic (E/E) architects finding the best application model for SWCs based on technical and economical criteria. State-of-the-art vehicle functions are finally used to test OptiCAM and analyze the results in more detail.

Notes for using the program:
The Java code was written and tested in Eclipse IDE with the WindowBuilder extension. For using the tool you have to install the WindowBuilder as well via

 Help --> Install New Software
 
Select all repositories with "-- All Available Sites--" and install all WindowBuilder extension under "General Purpose Tools" or "WindowBuilder" itself. The GUI then allows to calculate suitabilities according to the input parameters as shown in the screenshot:

![OPTICAM](https://github.com/sommsmartin/OptiCAM-MCDMCloudApplicationModels/assets/127199235/ee056be1-f932-46d1-9940-8554d7a87ac2)
