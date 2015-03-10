TARGET=ChessGame
PKGNAM=main
OUTDIR=bin
SRCDIR=src
IMGDIR=$(SRCDIR)/$(PKGNAM)

.PHONY: compile run clean jar runjar

compile:
	javac -d $(OUTDIR) -cp $(OUTDIR) -sourcepath $(SRCDIR) src/main/*.java

run: compile
	java -cp $(OUTDIR):$(IMGDIR) $(PKGNAM).$(TARGET)

clean:
	rm -rf $(OUTDIR)/* $(TARGET).jar manifest.mf

jar: compile
	echo "Main-Class: $(PKGNAM).$(TARGET)" > manifest.mf
	echo "Class-Path: $(IMGDIR)" >> manifest.mf
	jar cmvf manifest.mf $(TARGET).jar -C $(OUTDIR) . -C $(IMGDIR) .

runjar: jar
	java -jar $(TARGET).jar
