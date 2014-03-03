package uk.ac.glasgow.psd3.components.test;

import java.io.File;
import java.util.List;

import org.jbehave.core.io.StoryFinder;
import org.junit.Test;



public class DCSStories {

	@Test
	public void runURLLoadedStoriesAsJUnit() {
		DCSEmbedder embedder = new DCSEmbedder();
		
		List<String> storyPaths = 
			getStoryPathsFromProjectBinDir();
				
	    embedder.runStoriesAsPaths(storyPaths);
	}

	private List<String> getStoryPathsFromProjectBinDir() {
		
		StoryFinder finder = 
			new StoryFinder();
		
		File projectBinDir = 
			new File("./bin").getAbsoluteFile();
		
		String projectBinDirPath =
			projectBinDir.getPath();
					
		List<String> storyPaths =
			finder.findPaths(
				projectBinDirPath, "**/*.story", "");
		
		return storyPaths;
	}
}
