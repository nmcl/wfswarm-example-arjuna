OperatorTable addAssignOperator(":", "atPutField")

curlyBrackets := method(
	      r := Map clone
	      call message arguments foreach(arg,
	      	   r doMessage(arg))
	      r)

Map atPutField := method(
    self atPut(
    	 call evalArgAt(0) asMutable removePrefix("\"") removeSuffix("\""),
	 call evalArgAt(1));
)

Map dump := method(
    theKeys := self keys;
    theKeys foreach(key,
    	    key print; "=" print; "\"" print; self at(key) print; "\"" print));

depth := 0

whitespace := method (for (i, 0, depth, 1, " " print))

forward := method (
	self depth = self depth +1;
	self whitespace;

	attributes := false;

	write("<", call message name);

	call message arguments foreach(
	     arg,
	     content := self doMessage(arg);

	     if (content type == "Map", " " print; content dump; attributes := true));

	if (attributes == true, writeln("/>"), writeln(">");
		self whitespace;
			writeln("</", call message name, ">");
				self depth = self depth -1;
				))

s := File with("xml.txt") openForReading contents
xml := doString(s)