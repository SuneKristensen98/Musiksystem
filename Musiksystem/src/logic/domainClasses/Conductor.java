package logic.domainClasses;

public class Conductor {
	


		protected int conductorId;	
		protected String conductorName;
		

		public Conductor(int conductorId, String conductorName) {
			this.conductorId = conductorId;
			this.conductorName = conductorName;


		}


		public int getConductorId() {
			return conductorId;
		}


		public void setConductorId(int conductorId) {
			this.conductorId = conductorId;
		}

	

		public String getConductorName() {
			return conductorName;
		}
	

}
