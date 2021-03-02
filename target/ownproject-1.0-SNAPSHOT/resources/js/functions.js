function deleteDisciplines(){
    var items = $("input[type=checkbox]:checked");
    if (items.length==0){
        alert("Выберите дисциплины для удаления");
        return;
    }
    var ids;

    for(var i = 0; i < items.length; i++){
        if(ids == null){
            ids = "'" + $(items[i]).attr("value");
        }else{
            ids = ids + "','" + $(items[i]).attr("value");
        }
        if(i == items.length - 1){
            ids = ids + "'"
        }
    }
    $('#deleteDiscHidden').val(ids);
    $('#deleteDiscForm').submit();
}
function modifyingDiscipline(){
    var items = $("input[type=checkbox]:checked");
    if (items.length==0){
        alert("Выберите хотя бы одну дисциплину для изменения");
        return;
    }
    if (items.length>1){
        alert("Выберите только одну дисциплину для изменения");
        return;
    }
    var ids;

    for(var i = 0; i < items.length; i++){
        if(ids == null){
            ids = "'" + $(items[i]).attr("value");
        }else{
            ids = ids + "','" + $(items[i]).attr("value");
        }
        if(i == items.length - 1){
            ids = ids + "'"
        }
    }
    $('#modifyingDiscHidden').val(ids);
    $('#modifyingDiscForm').submit();
}
function deleteStudent(){
    var items = $("input[type=checkbox]:checked");
    if (items.length==0){
        alert("Выберите студента для удаления");
        return;
    }
    var ids;

    for(var i = 0; i < items.length; i++){
        if(ids == null){
            ids = "'" + $(items[i]).attr("value");
        }else{
            ids = ids + "','" + $(items[i]).attr("value");
        }
        if(i == items.length - 1){
            ids = ids + "'"
        }
    }
    $('#deleteStudentHidden').val(ids);
    $('#deleteStudentForm').submit();
}
function modifyingStudent(){
    var items = $("input[type=checkbox]:checked");
    if (items.length!=1){
        alert("Выберите одного студента для изменения");
        return;
    }
    var ids;

    for(var i = 0; i < items.length; i++){
        if(ids == null){
            ids = "'" + $(items[i]).attr("value");
        }else{
            ids = ids + "','" + $(items[i]).attr("value");
        }
        if(i == items.length - 1){
            ids = ids + "'"
        }
    }
    $('#modifyStudentHidden').val(ids);
    $('#modifyStudentForm').submit();
}
function gettingStudentProgress(){
    var items = $("input[type=checkbox]:checked");
    if (items.length!=1){
        alert("Выберите одного студента для просмотра успеваемости");
        return;
    }
    var ids;

    for(var i = 0; i < items.length; i++){
        if(ids == null){
            ids = "'" + $(items[i]).attr("value");
        }else{
            ids = ids + "','" + $(items[i]).attr("value");
        }
        if(i == items.length - 1){
            ids = ids + "'"
        }
    }
    $('#progressStudentHidden').val(ids);
    $('#progressStudentForm').submit();
}
function getValue() {
    var select = document.getElementById("deletingSemId");
    var value = select.value;
    $('#deleteSemIdHidden').val(value);
    $('#deleteSemIdForm').submit();
}
function getModifValue() {
    var select = document.getElementById("deletingSemId");
    var value = select.value;
    $('#modifySemesterIdHidden').val(value);
    $('#modifySemesterIdForm').submit();
}


