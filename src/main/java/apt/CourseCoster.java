package apt;

public class CourseCoster {
	private final IDbLayer db;
	
	public CourseCoster(IDbLayer db) {
		this.db = db;
	}
	
	public double getCost(String courseCode, String promotionCode) {
		double courseCost = db.getCourseCost(courseCode);
		double discount = db.getDiscount(promotionCode);
		return courseCost * (100 - discount) / 100;
	}
}
