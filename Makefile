OUTDIR=bin/
SRCDIR=src/

# How to run:
# java -classpath $(OUTDIR) package.Classname

default:
	javac -d $(OUTDIR) -cp $(OUTDIR) -sourcepath $(SRCDIR) src/main/*.java

clean:
	rm -rf bin/*.class
