package mark.chunking;

import gate.creole.PackagedController;
import gate.creole.metadata.AutoInstance;
import gate.creole.metadata.AutoInstanceParam;
import gate.creole.metadata.CreoleParameter;
import gate.creole.metadata.CreoleResource;

@CreoleResource(name = "Noun Phrase Chunker",
    icon = "NpChunker",
    comment = "Ready-made NP chunking application",
    autoinstances = @AutoInstance(parameters = {
        @AutoInstanceParam(name="pipelineURL", value="np-chunker.xgapp"),
        @AutoInstanceParam(name="menu", value="NP Chunking")}))
public class ChunkingApp extends PackagedController {

}
