// a compiled calendar.coffee
var Calendar, CalendarCell;

Calendar = (function() {
  function Calendar(_arg) {
    var col, row;
    this.cols = _arg.cols, this.rows = _arg.rows;
    this.data = (function() {
      var _i, _len, _ref, _results;
      _ref = this.rows;
      _results = [];
      for (_i = 0, _len = _ref.length; _i < _len; _i++) {
        row = _ref[_i];
        _results.push((function() {
          var _j, _len1, _ref1, _results1;
          _ref1 = this.cols;
          _results1 = [];
          for (_j = 0, _len1 = _ref1.length; _j < _len1; _j++) {
            col = _ref1[_j];
            _results1.push(new CalendarCell({}));
          }
          return _results1;
        }).call(this));
      }
      return _results;
    }).call(this);
  }

  Calendar.prototype.addCell = function(coln, rown, cell) {
    var col, i, row, _i, _ref, _results;
    row = this.rows.indexOf(rown);
    col = this.cols.indexOf(coln);
    this.data[row][col] = cell;
    if (cell.dur > 1) {
      _results = [];
      for (i = _i = 1, _ref = cell.dur; 1 <= _ref ? _i < _ref : _i > _ref; i = 1 <= _ref ? ++_i : --_i) {
        _results.push(this.data[row + i][col] = null);
      }
      return _results;
    }
  };

  Calendar.prototype.addSessions = function(sessions) {
    var s, _i, _len, _results;
    _results = [];
    for (_i = 0, _len = sessions.length; _i < _len; _i++) {
      s = sessions[_i];
      _results.push(this.addCell(s[0], s[1], CalendarCell.fromSession(s)));
    }
    return _results;
  };

  Calendar.prototype.render = function() {
    var $table, rown, _i, _len, _ref;
    $table = $('<table></table>');
    $table.append(this.getHeadings());
    _ref = this.rows;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      rown = _ref[_i];
      $table.append(this.getRow(rown, this.data[this.rows.indexOf(rown)]));
    }
    return $table;
  };

  Calendar.prototype.getHeadings = function() {
    var $row, coln, _i, _len, _ref;
    $row = $('<tr><th></th></tr>');
    _ref = this.cols;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      coln = _ref[_i];
      $row.append($("<th>" + coln + "</th>"));
    }
    return $row;
  };

  Calendar.prototype.getRow = function(rown, row) {
    var $row, cell, _i, _len;
    $row = $("<tr><th>" + rown + "</th></tr>");
    for (_i = 0, _len = row.length; _i < _len; _i++) {
      cell = row[_i];
      if (cell !== null) {
        $row.append(cell.render());
      }
    }
    return $row;
  };

  return Calendar;

})();

CalendarCell = (function() {
  function CalendarCell(_arg) {
    this.title = _arg.title, this.info = _arg.info, this.dur = _arg.dur, this.onclick = _arg.onclick;
    if (this.title == null) {
      this.title = '';
    }
    if (this.info == null) {
      this.info = '';
    }
    if (this.dur == null) {
      this.dur = 1;
    }
    if (this.onclick == null) {
      this.onclick = null;
    }
  }

  CalendarCell.prototype.render = function() {
    var $cell;
    $cell = $("<td rowspan=" + this.dur + "><h1>" + this.title + "</h1><p>" + this.info + "</p></td>");
    $cell.on('click', this.onclick);
    return $cell;
  };

  CalendarCell.fromSession = function(s) {
    return new CalendarCell({
      title: s[2],
      info: s[3],
      dur: s[4],
      onclick: function() { alert( s[5] ) }
    });
  };

  return CalendarCell;

})();