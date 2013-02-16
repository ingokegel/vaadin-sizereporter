# Vaadin SizeReporter

## Usage
Vaadin SizeReporter is an add-on for [Vaadin](https://vaadin.com). It requires at least Vaadin 7.
Specifically it will not work with Vaadin 6. To use it, you have to compile your own widgetset, using the
precompiled widget set will not work with this addon.

To get the size of a component in the browse, create an instance of SizeReporter:

    SizeReporter sizeReporter = new SizeReporter(component);

After the layout has been realized in the browser, you can query width and height:

    int width = sizeReporter.getWidth();
    int height = sizeReporter.getWidth();

You cannot query these values immediately when constructing the component. To wait for the
first size report and all subsequent resizes, register a `ComponentResizeListener`

    sizeReporter.addResizeListener(new ComponentResizeListener() {
         @Override
         public void sizeChanged(ComponentResizeEvent event) {
             // use event.getWidth() and  event.getHeight()
         }
     });

See the `TestUi` class for a runnable example.

## Build instructions

The addon is built with gradle. The default tasks builds the distribution JAR file in `build\libs`.
To compile the widgetset for running the Tomcat run configuration in the IntelliJ IDEA project,
run the "compileWidgetSet" gradle task first.
