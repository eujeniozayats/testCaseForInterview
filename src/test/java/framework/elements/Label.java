package framework.elements;

import org.openqa.selenium.By;

public class Label extends BaseElement {

    public Label(final By locator, final String name) {
        super(locator, name);
    }

    protected String getElementType() {
        return getLocale("loc.textlbl");
    }

}