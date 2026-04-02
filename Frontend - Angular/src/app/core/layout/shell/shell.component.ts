import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { GlobalHeaderComponent } from '../global-header/global-header.component';
@Component({
  selector: 'app-shell',
  imports: [RouterOutlet, GlobalHeaderComponent],
  templateUrl: './shell.component.html',
})
export class ShellComponent {
}
