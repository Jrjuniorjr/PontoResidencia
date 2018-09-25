import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[appFocus]'
})
export class FocusDirective {

  constructor(el:ElementRef) {
    setTimeout(() => { 
      if(el) // Vai que...
        el.nativeElement.focus() 
    }, 100) // Necessário em alguns casos (Stackoverflow)
   }

}
