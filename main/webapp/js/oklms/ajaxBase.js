/*============================================*/

/*                        AJAX 공통함수        */

/*============================================*/

/*----------- AJAX httpRequest Start ----------*/

var httpRequest = null;

function getXMLHttpRequest()
{
  if (window.ActiveXObject)
  {
    try
    {
      return new ActiveXObject("Msxml2.XMLHTTP");
    }
    catch(e) 
    {
      try 
      {
        return new ActiveXObject("Microsoft.XMLHTTP");
      }
      catch(e1)
      {
        return null;
      }
    }
  }
  else if (window.XMLHttpRequest)
  {
    return new XMLHttpRequest();
  }
  else
  {
    return null;
  }
}



function sendRequest(url, params, callback, method)
{
  httpRequest = getXMLHttpRequest();

  var httpMethod = method ? method : 'GET';

  if (httpMethod != 'GET' && httpMethod != 'POST')
  {
    httpMethod = 'GET';
  }

  var httpParams = (params == null || params == '') ? null : params;
  var httpUrl = url;

  if (httpMethod == 'GET' && httpParams != null)
  {
    httpUrl = httpUrl + "?" + httpParams;
  }
  httpRequest.open(httpMethod, httpUrl, true);
  httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  httpRequest.onreadystatechange = callback;
  httpRequest.send(httpMethod == 'POST' ? httpParams : null);
}

function setQueryString(form)
{
	var queryString ="";
	var org = form;
	var numberElements = obj.elements.length - 1 ;
	for (var i=0;  i < numberElements; i++)
	{
		if (i < numberElements - 1){
			queryString += obj.elements[i].name + "=" +
			escape(obj.elements[i].value)+"&"; 
		}
		else{
			queryString += obj.elements[i].name + "=" +
			escape(obj.elements[i].value); 
		}
	}
	return queryString;
}



