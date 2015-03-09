OUTDIR=bin/
SRCDIR=src/

# How to run:
# java -classpath $(OUTDIR) package.Classname

compile:
	javac -d $(OUTDIR) -cp $(OUTDIR) -sourcepath $(SRCDIR) src/main/*.java
run: compile
	java -cp $(OUTDIR) main.ChessGame
clean:
	rm -rf $(OUTDIR)*.class
