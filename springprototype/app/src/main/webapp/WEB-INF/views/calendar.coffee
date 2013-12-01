# a calendar library, for generating different types of calendars
class Calendar
	constructor: ({@cols, @rows}) ->
		@data = (new CalendarCell({}) for col in @cols for row in @rows)

	addCell: (coln, rown, cell) ->
		row = @rows.indexOf( rown )
		col = @cols.indexOf( coln )
		@data[row][col] = cell
		if cell.dur > 1
			@data[row+i][col] = null for i in [1...cell.dur]

	addSessions: (sessions) ->
		@addCell( s[0], s[1], CalendarCell.fromSession(s)) for s in sessions

	render: () ->
		$table = $('<table></table>');
		$table.append( @getHeadings() )
		$table.append( @getRow( rown, @data[ @rows.indexOf( rown ) ] ) ) for rown in @rows
		$table

	getHeadings: () ->
		$row = $('<tr><th></th></tr>')
		$row.append( $("<th>#{coln}</th>") ) for coln in @cols
		$row

	getRow: ( rown, row ) ->
		$row = $("<tr><th>#{rown}</th></tr>")
		$row.append( cell.render() ) for cell in row when cell isnt null
		$row

	deleteCell: (rown, coln) ->
		row = @rows.indexOf( rown )
		col = @cols.indexOf( coln )		
		cell = @data[row][col]		
		@data[row][col] = new CalendarCell({})
		if cell.dur > 1
			@data[row+i][col] = new CalendarCell({}) for i in [1...cell.dur]		

class CalendarCell
	constructor: ({@title, @info, @dur, @onclick}) ->
		@title ?= ''; @info ?= ''; @dur ?= 1; @onclick ?= null;

	render: ->
		$cell = $("<td rowspan=#{@dur}><h1>#{@title}</h1><p>#{@info}</p>"+(if @title then "<div class='deleter'>[del]</div>" else "")+"</td>")
		$cell.on( 'click', '.deleter', (event) ->
			row = $("th", $(this).parent().parent()).text()
			column = days[($.makeArray($("td", $(this).parent().parent()))).indexOf($(this).parent()[0])]

			index = -1
			for session in sessions
				if session[0] == column && session[1] == row
					index = sessions.indexOf(session)
					break

			if index > -1
				sessions.splice(index, 1)
				cal.deleteCell(row, column)
				cal.addSessions(sessions)

			$('#day-timetable').empty();
			$('#day-timetable').append(cal.render());
			event.stopPropagation();
		)

		$cell.on( 'click', @onclick )
		$cell
 
	@fromSession: (s) ->
		new CalendarCell( title: s[2], info: s[3], dur: s[4], onclick: -> alert s[5] )