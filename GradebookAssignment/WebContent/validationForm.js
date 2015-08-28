function validateForm() {
    var assignment = document.getElementById("Assignment").value;
    var grade = document.getElementById("Grades").value;
    if (assignment == null || assignment == "") {
        alert("Assignment must be filled out");
        return false;
    }
    
    if (grade == null || grade == "" || isNaN(grade)) {
        alert("Enter a valid grade!");
        return false;
    }
}