/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//TODO
function validate() {
    importClass(cat.urv.deim.sob.handlers.FormHandler);
    var formHandler = Java.type("FormHandler");
    alert($('#signupform\\:username').val());
    var username = $('#signupform\\:username').val();
    var email = $('#signupform\\:email').val();
    var password = $('#signupform\\:password').val();
    var passwordRepeat = $('#signupform\\:passwordRepeat').val();
}

