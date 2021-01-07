package com.example.testowanieCRUD.jbehave;

import com.example.testowanieCRUD.TestowanieCrudApplication;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.model.TableParsers;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.CONSOLE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestowanieCrudApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RunStories extends JUnitStories {


    @Autowired
    private ApplicationContext applicationContext;

    public RunStories() {
        initJBehaveConfiguration();
    }

    private void initJBehaveConfiguration() {
        final Class<? extends Embeddable> embeddableClass = this.getClass();
        final ParameterConverters parameterConverters = new ParameterConverters();
        final TableTransformers tableTransformers = new TableTransformers();
        final TableParsers tableParsers = new TableParsers();
        final ParameterControls parameterControls = new ParameterControls();
        final ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
                new LoadFromClasspath(embeddableClass),
                parameterConverters,
                parameterControls,
                tableParsers,
                tableTransformers);
        parameterConverters.addConverters(new ParameterConverters.DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
                new ParameterConverters.ExamplesTableConverter(examplesTableFactory));
        Configuration configuration = new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryParser(new RegexStoryParser(examplesTableFactory))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass)).withDefaultFormats()
                                .withFormats(CONSOLE)
                        //.withReporters(new MetaAllureJBehaveReporter())
                )
                .useParameterConverters(parameterConverters);
        useConfiguration(configuration);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(), applicationContext);
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*.story",
                "**/excluded*.story");
    }

}