package builder;

import content.Root;

import java.io.IOException;

public class RootBuilder extends GenericBuilder<RootBuilder> {
    @Override
    protected RootBuilder self() {
        return this;
    }

    public Root build() throws IOException {
        return new Root(id, content, children);
    }
}
