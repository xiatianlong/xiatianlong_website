(function (root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module unless amdModuleId is set
    define('simditor-emoji', ["jquery","simditor"], function (a0,b1) {
      return (root['EmojiButton'] = factory(a0,b1));
    });
  } else if (typeof exports === 'object') {
    // Node. Does not work with strict CommonJS, but
    // only CommonJS-like environments that support module.exports,
    // like Node.
    module.exports = factory(require("jquery"),require("Simditor"));
  } else {
    root['SimditorEmoji'] = factory(root["jQuery"],root["Simditor"]);
  }
}(this, function ($, Simditor) {

var EmojiButton,
  __hasProp = {}.hasOwnProperty,
  __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; },
  __slice = [].slice;

EmojiButton = (function(_super) {
  __extends(EmojiButton, _super);

  EmojiButton.i18n = {
    'zh-CN': {
      emoji: '表情'
    },
    'en-US': {
      emoji: 'emoji'
    }
  };

  EmojiButton.images = ['1.gif','2.gif','3.gif','4.gif','5.gif','6.gif','7.gif','8.gif','9.gif','10.gif'
    ,'11.gif','12.gif','13.gif','14.gif','15.gif','16.gif','17.gif','18.gif','19.gif','20.gif',
    '21.gif','22.gif','23.gif','24.gif','25.gif','26.gif','27.gif','28.gif','29.gif','30.gif',
    '31.gif','32.gif','33.gif','34.gif','35.gif','36.gif','37.gif','38.gif','39.gif','40.gif',
    '41.gif','42.gif','43.gif','44.gif','45.gif','46.gif','47.gif','48.gif','49.gif','50.gif',
    '51.gif','52.gif','53.gif','54.gif','55.gif','56.gif','57.gif','58.gif','59.gif','60.gif','61.gif','62.gif'];

  EmojiButton.prototype.name = 'emoji';

  EmojiButton.prototype.icon = 'smile-o';

  EmojiButton.prototype.menu = true;

  function EmojiButton() {
    var args;
    args = 1 <= arguments.length ? __slice.call(arguments, 0) : [];
    EmojiButton.__super__.constructor.apply(this, args);
    $.merge(this.editor.formatter._allowedAttributes['img'], ['data-emoji', 'alt']);
  }

  EmojiButton.prototype.renderMenu = function() {
    var $list, dir, html, name, opts, src, tpl, _i, _len, _ref;
    tpl = '<ul class="emoji-list">\n</ul>';
    opts = $.extend({
      imagePath: 'images/emoji/',
      images: EmojiButton.images
    }, this.editor.opts.emoji || {});
    html = "";
    dir = opts.imagePath.replace(/\/$/, '') + '/';
    _ref = opts.images;
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      name = _ref[_i];
      src = "" + dir + name;
      name = name.split('.')[0];
      html += "<li data-name='" + name + "'><img src='" + src + "' width='20' height='20' alt='" + name + "' /></li>";
    }
    $list = $(tpl);
    $list.html(html).appendTo(this.menuWrapper);
    return $list.on('mousedown', 'li', (function(_this) {
      return function(e) {
        var $img;
        _this.wrapper.removeClass('menu-on');
        if (!_this.editor.inputManager.focused) {
          return;
        }
        $img = $(e.currentTarget).find('img').clone().attr({
          'data-emoji': true,
          'data-non-image': true
        });
        _this.editor.selection.insertNode($img);
        _this.editor.trigger('valuechanged');
        _this.editor.trigger('selectionchanged');
        return false;
      };
    })(this));
  };

  EmojiButton.prototype.status = function() {};

  return EmojiButton;

})(Simditor.Button);

Simditor.Toolbar.addButton(EmojiButton);

return EmojiButton;

}));
