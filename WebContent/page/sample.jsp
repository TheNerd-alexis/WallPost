<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">				
		<title>Sticky Note Demo</title>
		<link rel="shortcut icon" href="favicon.ico"/>
		<link type="text/css" rel="stylesheet" href="css/main.css"/>		
		<link href="http://fonts.googleapis.com/css?family=Chewy:regular" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="js/jquery/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="js/json2.js"></script>
		
		<script type="text/javascript">
var notes, count = 0;

// save the notes into local storage
function saveNotes() {
    var notesArray = [];

    // for each of the notes add a bespoke note object to the array
    notes.find("li > div").each(function (i, e) {
        // save the class attribute of the div, as well as the text for the title and content text areas
        var colourClass = $(e).attr("class");
        var title = $(e).find("textarea.note-title");
        var content = $(e).find("textarea.note-content");

        notesArray.push({ Index: i, Title: title.val(), Content: content.val(), Class: colourClass });
    });

    // json encode it
    var jsonStr = JSON.stringify(notesArray);

    // and save the json string into local storage
    localStorage.setItem("notes", jsonStr);
}

// add event handlers to a note
function addNoteEvent(noteElement) {
    var div = noteElement.children("div");
    var closeImg = div.find("img");

    div.focus(function () {
        closeImg.removeClass("hide");
    });

    div.children().focus(function () {
        closeImg.removeClass("hide");
    });

    div.hover(function () {
        closeImg.removeClass("hide");
    }, function () {
        closeImg.addClass("hide");
        saveNotes();
    });

    div.children().hover(function () {
        closeImg.removeClass("hide");
    }, function () {
        closeImg.addClass("hide");
    });
}
			
//  adds a new note to the 'notes' list
function addNewNote(className, title, content) {
	// if class is not specified, use a random colour class
	if (!className) {
		className = "colour" + Math.ceil(Math.random() * 3);
	}
				
	// add a new note to the end of the list
	notes.append("<li><div class='" + className + "'>" + 
					"<textarea class='note-title' placeholder='Untitled' maxlength='10'/>" + 
					"<textarea class='note-content' placeholder='Your content here'/>" + 
					"</div></li>");
				
	// get the new note that's just been added and attach the click event handler to its close button
	var newNote = notes.find("li:last");
	newNote.find("img").click(function () {
        // remove the note and save
	    newNote.remove();
	    saveNotes();
	});
				
	// hook up event handlers to show/hide close button as appropriate
	addNoteEvent(newNote);
				
	// if a title is provided then set the title of the new note
	if (title) {
		// get the title textarea element and set its value
		newNote.find("textarea.note-title").val(title);
    }

	// if a content is provided then set the content of the new note
	if (content) {
		// get the content textarea element and set its value
		newNote.find("textarea.note-content").val(content);
    }
    // save
    saveNotes();
}

// load the notes saved in the local storage
function loadNotes() {
    var storedNotes = localStorage.getItem("notes");
    if (storedNotes) {
        // passes the stored json back into an array of note objects
        var notesArray = JSON.parse(storedNotes);
        count = notesArray.length;

        var i;
        for (i = 0; i < count; i++) {
            var storedNote = notesArray[i];
            addNewNote(storedNote.Class, storedNote.Title, storedNote.Content);
        }
    }
}

$(document).ready(function () {
    // get references to the 'notes' list
    notes = $("#notes");

    // load notes from local storage if one's available
    loadNotes();

    // clicking the 'New Note' button adds a new note to the list
    $("#btnNew").click(function () {
        addNewNote();
    });

    // add a note to the list if there aren't any
    if (count === 0) {
        $("#btnNew").click();
    }
});
		</script>
	</head>
	
	<body>
		<div id="controls">
			<img id="btnNew" src="images/newnote.png"/>
		</div>
		<ul id="notes"/>        
	</body>
</html>