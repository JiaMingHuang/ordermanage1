$(function(){
	$('.add_btn').click(function(){
		var tempTr = $('<tr>');
		for(var i = 0; i < 4; i++){
			tempTr.append('<td><input type="text" class="form-control"></td>');
		}
		tempTr.append('<td><div class="btn-group"><button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">点击选择工厂 <span class="caret"></span></button>'
					+ '<ul class="dropdown-menu">'
					+ '<li><a>工厂名称 1</a></li>'
					+ '<li><a>工厂名称 2</a></li>'
					+ '<li><a>工厂名称 3</a></li>'
					+ '</ul>'
				+ '</div>'
	    	+ '</td>');
		var tempBtn = $('<button class="btn btn-danger">删除</button>');
		tempBtn.click(delete_item);
		tempTr.find('.dropdown-menu').click(chooseFactory);
		tempTr.append($('<td class="delete_column"></td>').append(tempBtn));
		$(".add_container").before(tempTr);
	})

});
function delete_item(){
	$(this).parents("tr").remove();
}

function chooseFactory(event){
	$(event.currentTarget).siblings('.dropdown-toggle').html($(event.target).text() + ' <span class="caret"></span>');
}