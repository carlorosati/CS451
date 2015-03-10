OUTDIR=bin
SRCDIR=src
PKGNAM=main
IMGDIR=$(SRCDIR)/$(PKGNAM)

# How to run:
# java -classpath $(OUTDIR) package.Classname

compile:
	javac -d $(OUTDIR) -cp $(OUTDIR) -sourcepath $(SRCDIR) src/main/*.java
run: compile
	java -cp $(OUTDIR):$(IMGDIR) $(PKGNAM).ChessGame
clean:
	rm -rf $(OUTDIR)*.class
