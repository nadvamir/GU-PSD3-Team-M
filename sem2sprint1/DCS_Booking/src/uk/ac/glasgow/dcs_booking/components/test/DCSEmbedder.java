package uk.ac.glasgow.dcs_booking.components.test;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryLoader;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.StepPatternParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.StepMonitor;

import uk.ac.glasgow.dcs_booking.components.test.stories.steps.AllTheSteps;
import uk.ac.glasgow.dcs_booking.components.test.stories.steps.Story_14;
import uk.ac.glasgow.dcs_booking.components.test.stories.steps.Story_8;
import uk.ac.glasgow.dcs_booking.components.test.stories.steps.Story_NF_p0;
import uk.ac.glasgow.dcs_booking.components.test.stories.steps.Story_NF_p1;
import uk.ac.glasgow.dcs_booking.components.test.stories.steps.Story_NF_p2;
import uk.ac.glasgow.dcs_booking.components.test.stories.steps.Story_NF_p3;
import uk.ac.glasgow.dcs_booking.components.test.stories.steps.Story_NF_p4;
import uk.ac.glasgow.dcs_booking.components.test.stories.steps.Story_NF_s0;



public class DCSEmbedder extends Embedder {
 
    @Override
    public InjectableStepsFactory stepsFactory() {
    	ArrayList list = new ArrayList();
    	list.add(new AllTheSteps());
    	list.add(new Story_8());
    	list.add(new Story_14());
    	list.add(new Story_NF_p0());
    	list.add(new Story_NF_p1());
    	list.add(new Story_NF_p2());
    	list.add(new Story_NF_p3());
    	list.add(new Story_NF_p4());
    	list.add(new Story_NF_s0());
    	
        return
        	new InstanceStepsFactory(
        		configuration(), list);
    }
    
    //...
	
	@Override
    public EmbedderControls embedderControls() {
        
		EmbedderControls controls
        	= new EmbedderControls();
		controls.doIgnoreFailureInStories(false);
		controls.doIgnoreFailureInView(true);
		
		return controls;
    }
 
    @Override
    public Configuration configuration() {
    	        
        MostUsefulConfiguration configuration =
        	new MostUsefulConfiguration();
        
        ClassLoader classLoader =
        	this.getClass().getClassLoader();
        
		StoryLoader loader = 
        	new LoadFromClasspath(classLoader);

        configuration.useStoryLoader(loader);
        
        StoryReporterBuilder builder =
        	getStoryReporterBuilder();
        configuration.useStoryReporterBuilder(builder);
        
        ParameterConverters converters =
        	getParameterConverters();
        configuration.useParameterConverters(converters);
        
        StepPatternParser stepPatternParser = 
        	new RegexPrefixCapturingPatternParser("$");
		configuration.useStepPatternParser(stepPatternParser);
		
		StepMonitor stepMonitor = 
			new SilentStepMonitor();
        configuration.useStepMonitor(stepMonitor);
        
        return configuration;

    }

	private StoryReporterBuilder getStoryReporterBuilder() {
		
		StoryReporterBuilder builder =
			new StoryReporterBuilder();
		
        Class<? extends DCSEmbedder> embedderClass =
            this.getClass();
        
        URL codeLocation =
        	CodeLocations.codeLocationFromClass(embedderClass);
		
        builder.withCodeLocation(codeLocation);
        
        builder.withFormats(Format.STATS,Format.HTML,Format.XML);
                
        builder.withCrossReference(new CrossReference());
		return builder;
	}

	private ParameterConverters getParameterConverters() {
		ParameterConverters converters =
			new ParameterConverters();
        
        SimpleDateFormat dateFormat = 
        	new SimpleDateFormat("yyyy-MM-dd");
        
        DateConverter dateConverter =
        	new DateConverter(dateFormat);
        
        converters.addConverters(dateConverter);
		return converters;
	}
}
