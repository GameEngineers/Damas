package es.urjccode.mastercloudapps.adcs.dobles.injectMocks;

public class SUT {

    private DOC doc;

	private int attribute;

	public SUT() {
        this.doc = new DOC();
	}

	public void exerciseSUT(boolean value) {
		this.attribute = doc.get();
		this.doc.set(this.attribute-1);
		System.out.println("SUT: " + this.attribute);
		doc.exerciseDOC(value);
	}
    
}